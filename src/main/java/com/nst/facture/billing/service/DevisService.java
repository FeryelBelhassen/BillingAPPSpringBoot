package com.nst.facture.billing.service;

import com.nst.facture.billing.models.Devis;

import java.util.List;

public interface DevisService {
    public List<Devis> getAllDevis();

    Devis createDevis(Devis devis);

    Devis updateDevis(long id, Devis devis);

    void deleteDevis(long id);

    Devis getDevisById(long id);


}
