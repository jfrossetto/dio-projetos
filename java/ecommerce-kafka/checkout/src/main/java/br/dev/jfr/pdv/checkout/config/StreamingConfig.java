package br.dev.jfr.pdv.checkout.config;

import br.dev.jfr.pdv.checkout.streaming.CheckoutCreatedSource;
import br.dev.jfr.pdv.checkout.streaming.PaymentPaidSink;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(value = {
        CheckoutCreatedSource.class,
        PaymentPaidSink.class
})
public class StreamingConfig {
}
