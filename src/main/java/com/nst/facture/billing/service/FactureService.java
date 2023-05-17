package com.nst.facture.billing.service;

import com.nst.facture.billing.models.Facture;
import com.nst.facture.billing.payload.Dto.FactureDto;

import java.util.List;

public interface FactureService {
    public List<Facture> getAllFactures();

    Facture addFactureFromDTO(FactureDto factureDto);

    Facture updateFacture(Long id, Facture facture);

    void deleteFacture(Long id);

    Facture getFactureById(long id);

    List getFacturesByClient(String client);


}
