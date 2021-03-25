package br.dev.jfr.pdv.payment.listener;

import br.dev.jfr.pdv.checkout.event.CheckoutCreatedEvent;
import br.dev.jfr.pdv.payment.event.PaymentCreatedEvent;
import br.dev.jfr.pdv.payment.model.Payment;
import br.dev.jfr.pdv.payment.services.PaymentService;
import br.dev.jfr.pdv.payment.streaming.CheckoutProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckoutCreatedListener {

    private final CheckoutProcessor checkoutProcessor;

    private final PaymentService paymentService;

    @StreamListener(CheckoutProcessor.INPUT)
    public void handler(CheckoutCreatedEvent checkoutCreatedEvent) {
        log.info("checkoutCreatedEvent={}", checkoutCreatedEvent);
        final Payment payment = paymentService.create(checkoutCreatedEvent).orElseThrow();
        final PaymentCreatedEvent paymentCreatedEvent = PaymentCreatedEvent.newBuilder()
                .setCheckoutCode(payment.getCheckoutCode())
                .setPaymentCode(payment.getCode())
                .build();
        checkoutProcessor.output().send(MessageBuilder.withPayload(paymentCreatedEvent).build());
    }

}
