package br.dev.jfr.pdv.checkout.services;

import br.dev.jfr.pdv.checkout.dto.CheckoutRequest;
import br.dev.jfr.pdv.checkout.model.Checkout;

import java.util.List;
import java.util.Optional;

public interface CheckoutService {

    List<Checkout> findAll();

    Optional<Checkout> create(CheckoutRequest checkoutRequest);

    Optional<Checkout> updateStatus(String checkoutCode, Checkout.Status status);
}
