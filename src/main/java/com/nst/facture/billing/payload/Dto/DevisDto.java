package com.nst.facture.billing.payload.Dto;

import com.nst.facture.billing.models.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DevisDto {
    private long numerodevis;
    private Date datedevis;
    private ArrayList<Product> product;
    private Double price;

    }