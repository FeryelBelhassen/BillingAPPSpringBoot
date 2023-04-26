package com.nst.facture.billing.payload.Dto;

import com.nst.facture.billing.models.Client;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FactureAvoirDto {
    private long num_factureavoir;
    private Client client;
    private Date date_facture;
    private String designation;
    private long quantity;
    private Float montant_ttc;
    private Float montant_ht;
    }