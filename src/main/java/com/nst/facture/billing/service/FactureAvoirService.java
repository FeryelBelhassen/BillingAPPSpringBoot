package com.nst.facture.billing.service;

import com.nst.facture.billing.models.FactureAvoir;
import com.nst.facture.billing.payload.Dto.FactureAvoirDto;

import java.util.List;

public interface FactureAvoirService {
    public List<FactureAvoir> getAllFacturesAvoir();

    FactureAvoir addFactureAvoirFromDTO(FactureAvoirDto factureAvoirDto);

    FactureAvoir updateFactureAvoir(Long id, FactureAvoir factureAvoir);

    void deleteFactureAvoir(Long id);

    FactureAvoir getFactureAvoirById(long id);



}
