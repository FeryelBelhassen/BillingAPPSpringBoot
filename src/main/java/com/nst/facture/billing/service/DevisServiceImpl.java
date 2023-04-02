package com.nst.facture.billing.service;

import com.nst.facture.billing.models.Devis;
import com.nst.facture.billing.repository.DevisRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DevisServiceImpl implements DevisService {
    private final DevisRepository devisRepository;

    public DevisServiceImpl(DevisRepository devisRepository) {
        super();
        this.devisRepository = devisRepository;
    }
    @Override
    public List <Devis> getAllDevis() {

        return (List<Devis>) devisRepository.findAll();
    }

    @Override
    public Devis createDevis(Devis devis) {

        return devisRepository.save(devis);
    }

    @Override
    public Devis updateDevis(long id, Devis devisRequest) {
        Devis devis = devisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Devis"));

        devis.setNumerodevis(devisRequest.getNumerodevis());
        devis.setDatedevis(devisRequest.getDatedevis());
        devis.setQuantity(devisRequest.getQuantity());
        devis.setPrice(devisRequest.getPrice());
        return devisRepository.save(devis);
    }

    @Override
    public void deleteDevis(long id) {
        Devis devis = devisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Devis"));

        devisRepository.delete(devis);
    }

    @Override
    public Devis getDevisById(long id) {
        Optional<Devis> result = devisRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new ResourceNotFoundException("Devis");
        }
    }


}