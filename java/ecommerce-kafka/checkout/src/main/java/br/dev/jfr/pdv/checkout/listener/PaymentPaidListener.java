package br.dev.jfr.pdv.checkout.listener;

import br.dev.jfr.pdv.checkout.model.Checkout;
import br.dev.jfr.pdv.checkout.services.CheckoutService;
import br.dev.jfr.pdv.checkout.streaming.PaymentPaidSink;
import lombok.RequiredArgsConstructor;
import br.dev.jfr.pdv.payment.event.PaymentCreatedEvent;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentPaidListener {

    private final CheckoutService checkoutService;

    @StreamListener(PaymentPaidSink.INPUT)
    public void handler(PaymentCreatedEvent paymentCreatedEvent) {
        checkoutService.updateStatus(paymentCreatedEvent.getCheckoutCode().toString(),
                paymentCreatedEvent.getPaymentCode().toString(),
                Checkout.Status.APPROVED);
    }
}
