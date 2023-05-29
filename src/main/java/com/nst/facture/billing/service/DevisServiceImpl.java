package com.nst.facture.billing.service;

import com.nst.facture.billing.models.Devis;
import com.nst.facture.billing.payload.Dto.DevisDto;
import com.nst.facture.billing.repository.DevisRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
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
    public List<Devis> getAllDevis() {

        return (List<Devis>) devisRepository.findAll();
    }

    @Override
    public Devis addDevisFromDTO(DevisDto devisDto){
        Devis toAdd = new Devis();
        BeanUtils.copyProperties(devisDto, toAdd);
        return devisRepository.save(toAdd);
    }

    @Override
    public Devis updateDevis(Long id, Devis devis) {
        Devis devisDB =getDevisById(id);
        devisDB.setNumerodevis(devis.getNumerodevis());
        devisDB.setDatedevis(devis.getDatedevis());
        devisDB.setProduct(devis.getProduct());
        devisDB.setPrice(devis.getPrice());
        Devis updatedDevis = getDevisById(id);

        return updatedDevis;
    }



    @Override
    public void deleteDevis(Long id) {
        Optional<Devis> devis = devisRepository.findById(id);
        devis.ifPresent(d -> {
            devisRepository.delete(d);

        });
    }

    @Override
    public Devis getDevisById(long id) {
        return devisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Devis not found"));
    }

}