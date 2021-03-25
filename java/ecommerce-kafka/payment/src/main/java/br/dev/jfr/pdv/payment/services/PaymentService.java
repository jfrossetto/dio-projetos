package br.dev.jfr.pdv.payment.services;

import br.dev.jfr.pdv.checkout.event.CheckoutCreatedEvent;
import br.dev.jfr.pdv.payment.model.Payment;
import br.dev.jfr.pdv.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    public List<Payment> findAll();

    public Optional<Payment> create(CheckoutCreatedEvent checkoutCreatedEvent);
}
