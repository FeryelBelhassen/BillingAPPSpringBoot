package com.nst.facture.billing.payload.Dto;

import com.nst.facture.billing.models.Client;
import com.nst.facture.billing.models.Product;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FactureAvoirDto {
    private long numfactureavoir;
    private Client client;
    private List<Product> product;
    private Date datefacture;
    private double price;
    }
