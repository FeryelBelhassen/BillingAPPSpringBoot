package com.nst.facture.billing.service;

import com.nst.facture.billing.models.Devis;
import com.nst.facture.billing.payload.Dto.DevisDto;

import java.util.List;

public interface DevisService {
    public List<Devis> getAllDevis();

    Devis addDevisFromDTO(DevisDto devisDto);

    Devis updateDevis(Long id, Devis devis);

    void deleteDevis(Long id);

    Devis getDevisById(long id);


}
