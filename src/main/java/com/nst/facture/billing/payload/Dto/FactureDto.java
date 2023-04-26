package com.nst.facture.billing.payload.Dto;

import com.nst.facture.billing.models.Client;
import lombok.*;

import java.util.Date;
import java.util.List;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FactureDto {
    private long numerofacture;
    private Client client;
    private Date datefacture;
    private Float montanttc;
    private Float montantht;
    private List productList;


    }