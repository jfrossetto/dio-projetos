package br.dev.jfr.pdv.payment.repository;

import br.dev.jfr.pdv.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
