package br.dev.jfr.pdv.payment.resources;

import br.dev.jfr.pdv.payment.model.Payment;
import br.dev.jfr.pdv.payment.repository.PaymentRepository;
import br.dev.jfr.pdv.payment.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/v1/payment")
@RequiredArgsConstructor
public class PaymentResource {

    private final PaymentService paymentService;

    @GetMapping("/all")
    public ResponseEntity<List<Payment>> findAll() {
        return ResponseEntity.ok().body(paymentService.findAll());
    }

}
