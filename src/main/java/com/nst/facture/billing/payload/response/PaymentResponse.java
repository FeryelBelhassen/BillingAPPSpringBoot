package com.nst.facture.billing.payload.response;
import lombok.Data;

@Data
public class PaymentResponse {
    /**
     * The response from Stripe
     */
        private String id;
}
