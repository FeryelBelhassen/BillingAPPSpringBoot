package com.nst.facture.billing.payload.Dto;

import java.util.Date;

public class FactureDto {
    private long id;
    private long numero_facture;
    private long id_client;
    private Date date_facture;
    private Float montant_ttc;
    private Float montant_ht;


    }