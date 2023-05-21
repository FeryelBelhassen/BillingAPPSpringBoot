package com.nst.facture.billing.controllers.payment;

import com.nst.facture.billing.models.Payment;
import com.nst.facture.billing.payload.response.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles Stripe for credit cards
 */
@Slf4j
@RestController
public class PaymentController {

    public PaymentController(@Value("${stripe.apikey}") String apiKey) {
        Stripe.apiKey = apiKey;
    }

    /**
     * Creates a new payment from a Stripe.js token
     * @param payment
     * @return {@link ResponseEntity}
     */
    @PostMapping(value="/payment", consumes = "application/json")
    public ResponseEntity<?> PaymentCard(@RequestBody Payment payment) {
        log.info("Processing token " + payment);
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("amount", payment.getPrice());
            params.put("currency", payment.getCurrency());
            params.put("description", payment.getDescription());
            params.put("source", payment.getToken());
            Charge charge = Charge.create(params);
            PaymentResponse response = new PaymentResponse();
            response.setId(charge.getId());
            return ResponseEntity.ok(response);
        } catch(StripeException e) {
            return ResponseEntity.badRequest().body(e.getStripeError().getMessage());
        }
    }


}