package com.nst.facture.billing.models;

import lombok.Data;

@Data

public class Payment {
        private String token;
        private Long price;
        private String currency;
        private String description;

}

