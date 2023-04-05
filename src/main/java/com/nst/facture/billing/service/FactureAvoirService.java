package com.nst.facture.billing.service;

import com.nst.facture.billing.models.FactureAvoir;

import java.util.List;

public interface FactureAvoirService {
    public List<FactureAvoir> getAllFacturesAvoir();

    FactureAvoir createFactureAvoir(FactureAvoir factureavoir);

    FactureAvoir updateFactureAvoir(long idfactavoir, FactureAvoir factureavoir);

    void deleteFactureAvoir(long idfactavoir);

    FactureAvoir getFactureAvoirById(long idfactavoir);


}
