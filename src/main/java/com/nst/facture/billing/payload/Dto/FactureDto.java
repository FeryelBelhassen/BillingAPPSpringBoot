package com.nst.facture.billing.payload.Dto;

import com.nst.facture.billing.models.Client;

import java.util.Date;
import java.util.List;

public class FactureDto {
    private long id;
    private long numero_facture;
    private Client client;
    private Date date_facture;
    private Float montant_ttc;
    private Float montant_ht;
    private List ProductList;


    }