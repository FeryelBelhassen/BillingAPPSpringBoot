package com.nst.facture.billing.service;

import com.nst.facture.billing.models.FactureAvoir;
import com.nst.facture.billing.repository.FactureAvoirRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FactureAvoirServiceImpl implements FactureAvoirService {
    private final FactureAvoirRepository factureavoirRepository;

    public FactureAvoirServiceImpl(FactureAvoirRepository factureavoirRepository) {
        super();
        this.factureavoirRepository = factureavoirRepository;
    }


    @Override
    public List<FactureAvoir> getAllFacturesAvoir() {
        return (List<FactureAvoir>) factureavoirRepository.findAll();
    }

    @Override
    public FactureAvoir createFactureAvoir(FactureAvoir factureavoir) {
        return factureavoirRepository.save(factureavoir);
    }

    @Override
    public FactureAvoir updateFactureAvoir(long idfactavoir, FactureAvoir factureavoirRequest) {
        FactureAvoir factureAvoir = factureavoirRepository.findById(idfactavoir)
                .orElseThrow(() -> new ResourceNotFoundException("FactureAvoir"));

        factureAvoir.setNumfactureavoir(factureavoirRequest.getNumfactureavoir());
        factureAvoir.setClient(factureavoirRequest.getClient());
        factureAvoir.setDatefacture(factureavoirRequest.getDatefacture());
        factureAvoir.setDesignation(factureavoirRequest.getDesignation());
        factureAvoir.setQuantity(factureavoirRequest.getQuantity());
        factureAvoir.setMontanttc(factureavoirRequest.getMontanttc());
        factureAvoir.setMontantht(factureavoirRequest.getMontantht());
        return factureavoirRepository.save(factureAvoir);
    }

    @Override
    public void deleteFactureAvoir(long idfactavoir) {
        FactureAvoir factureAvoir = factureavoirRepository.findById(idfactavoir)
                .orElseThrow(() -> new ResourceNotFoundException("FactureAvoir"));

        factureavoirRepository.delete(factureAvoir);
    }

    @Override
    public FactureAvoir getFactureAvoirById(long idfactavoir) {
        Optional<FactureAvoir> result = factureavoirRepository.findById(idfactavoir);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new ResourceNotFoundException("FactureAvoir ");
        }
    }


}