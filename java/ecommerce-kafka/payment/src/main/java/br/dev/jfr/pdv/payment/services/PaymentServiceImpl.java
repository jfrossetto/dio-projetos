package br.dev.jfr.pdv.payment.services;

import br.dev.jfr.pdv.checkout.event.CheckoutCreatedEvent;
import br.dev.jfr.pdv.payment.model.Payment;
import br.dev.jfr.pdv.payment.repository.PaymentRepository;
import br.dev.jfr.pdv.payment.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UUIDUtil uuidUtil;

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> create(CheckoutCreatedEvent checkoutCreatedEvent) {
        final Payment paymentEntity = Payment.builder()
                .checkoutCode(checkoutCreatedEvent.getCheckoutCode())
                .code("PAY-"+uuidUtil.createUUID().toString())
                .build();
        paymentRepository.save(paymentEntity);
        return Optional.of(paymentEntity);
    }

}
