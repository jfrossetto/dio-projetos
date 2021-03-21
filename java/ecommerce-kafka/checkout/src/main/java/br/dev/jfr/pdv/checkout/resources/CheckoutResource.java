package br.dev.jfr.pdv.checkout.resources;

import br.dev.jfr.pdv.checkout.dto.CheckoutRequest;
import br.dev.jfr.pdv.checkout.dto.CheckoutResponse;
import br.dev.jfr.pdv.checkout.model.Checkout;
import br.dev.jfr.pdv.checkout.services.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/v1/checkout")
@RequiredArgsConstructor
public class CheckoutResource {

    private final CheckoutService checkoutService;

    @GetMapping("/all")
    public ResponseEntity<List<Checkout>> findAll() {
        return ResponseEntity.ok().body(checkoutService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<CheckoutResponse> create(@RequestBody CheckoutRequest checkoutRequest) {
        final Checkout checkout = checkoutService.create(checkoutRequest).orElseThrow();
        final CheckoutResponse checkoutResponse = CheckoutResponse.builder()
                .code(checkout.getCode())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(checkoutResponse);
    }
}
