package com.nst.facture.billing.service;

import com.nst.facture.billing.models.Facture;

import java.util.List;

public interface FactureService {
    public List<Facture> getAllFactures();

    Facture createFacture(Facture facture);

    Facture updateFacture(long id, Facture facture);

    void deleteFacture(long id);

    Facture getFactureById(long id);


}
