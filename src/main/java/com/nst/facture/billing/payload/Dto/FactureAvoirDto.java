package com.nst.facture.billing.payload.Dto;

import com.nst.facture.billing.models.Client;

import java.util.Date;

public class FactureAvoirDto {
    private long idfactavoir;
    private long num_factureavoir;
    private Client client;
    private Date date_facture;
    private String designation;
    private int quantity;
    private Float montant_ttc;
    private Float montant_ht;


    }