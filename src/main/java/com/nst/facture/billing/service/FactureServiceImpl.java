package com.nst.facture.billing.service;

import com.nst.facture.billing.models.Facture;
import com.nst.facture.billing.repository.FactureRepository;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public class FactureServiceImpl implements FactureService {
    private final FactureRepository factureRepository;

    public FactureServiceImpl(FactureRepository factureRepository) {
        super();
        this.factureRepository = factureRepository;
    }

    @Override
    public List<Facture> getAllFactures() {
        return (List<Facture>) factureRepository.findAll();
    }

    @Override
    public Facture createFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    @Override
    public Facture updateFacture(long id, Facture factureRequest) {
        Facture facture = factureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facture"));

        facture.setNumero(factureRequest.getNumero());
        facture.setClientId(facture.getClientId());
        facture.setDateFacture(facture.getDateFacture());
        facture.setDateValidation(facture.getDateValidation());
        facture.setMontantTTC(facture.getMontantTTC());
        facture.setMontantHT(facture.getMontantHT());
        facture.setAnnee(facture.getAnnee());
        return factureRepository.save(facture);
    }

    @Override
    public void deleteFacture(long id) {
        Facture facture = factureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facture"));

        factureRepository.delete(facture);
    }

    @Override
    public Facture getFactureById(long id) {
        Optional<Facture> result = factureRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("Facture ");
        }

    }
}