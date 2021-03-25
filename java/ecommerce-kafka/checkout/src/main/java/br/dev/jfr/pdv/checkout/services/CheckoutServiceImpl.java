package br.dev.jfr.pdv.checkout.services;

import br.dev.jfr.pdv.checkout.dto.CheckoutRequest;
import br.dev.jfr.pdv.checkout.event.CheckoutCreatedEvent;
import br.dev.jfr.pdv.checkout.model.Checkout;
import br.dev.jfr.pdv.checkout.model.CheckoutItem;
import br.dev.jfr.pdv.checkout.model.Shipping;
import br.dev.jfr.pdv.checkout.repository.CheckoutRepository;
import br.dev.jfr.pdv.checkout.streaming.CheckoutCreatedSource;
import br.dev.jfr.pdv.checkout.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutRepository repo;
    private final CheckoutCreatedSource checkoutCreatedSource;

    private final UUIDUtil uuidUtil;

    @Override
    public List<Checkout> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Checkout> create(CheckoutRequest checkoutRequest) {
        log.info("M=create, checkoutRequest={}", checkoutRequest);
        final Checkout checkout = Checkout.builder()
                .code(uuidUtil.createUUID().toString())
                .status(Checkout.Status.CREATED)
                .saveAddress(checkoutRequest.getSaveAddress())
                .saveInformation(checkoutRequest.getSaveInfo())
                .shipping(Shipping.builder()
                        .address(checkoutRequest.getAddress())
                        .complement(checkoutRequest.getComplement())
                        .country(checkoutRequest.getCountry())
                        .state(checkoutRequest.getState())
                        .cep(checkoutRequest.getCep())
                        .build())
                .build();
        checkout.setItems(checkoutRequest.getProducts()
                .stream()
                .map(product -> CheckoutItem.builder()
                        .checkout(checkout)
                        .product(product)
                        .build())
                .collect(Collectors.toList()));
        final Checkout entity = repo.save(checkout);

        final CheckoutCreatedEvent checkoutCreatedEvent = CheckoutCreatedEvent.newBuilder()
                .setCheckoutCode(entity.getCode())
                .setStatus(entity.getStatus().name())
                .build();
        checkoutCreatedSource.output().send(MessageBuilder.withPayload(checkoutCreatedEvent).build());

        return Optional.of(entity);
    }

    @Override
    public Optional<Checkout> updateStatus(String checkoutCode, Checkout.Status status) {
        final Checkout checkout = repo.findByCode(checkoutCode).orElse(Checkout.builder().build());
        checkout.setStatus(Checkout.Status.APPROVED);
        return Optional.of(repo.save(checkout));
    }
}
