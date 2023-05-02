package com.nst.facture.billing.service;

import com.nst.facture.billing.models.Facture;
import com.nst.facture.billing.payload.Dto.FactureDto;
import com.nst.facture.billing.repository.ClientRepository;
import com.nst.facture.billing.repository.FactureRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FactureServiceImpl implements FactureService {
    private final FactureRepository factureRepository;
    private final ClientRepository clientRepository;

    public FactureServiceImpl(FactureRepository factureRepository, ClientRepository clientRepository) {
        super();
        this.factureRepository = factureRepository;
        this.clientRepository = clientRepository;
    }
    @Override
    public List<Facture> getAllFactures() {

        return (List<Facture>) factureRepository.findAll();
    }

    @Override
    public Facture addFactureFromDTO(FactureDto factureDto){
        Facture toAdd = new Facture();
        BeanUtils.copyProperties(factureDto, toAdd);
        return factureRepository.save(toAdd);
    }

    @Override
    public Facture updateFacture(Long id, Facture facture) {
        Facture factureDB =getFactureById(id);
        factureDB.setNumerofacture(facture.getNumerofacture());
        factureDB.setDatefacture(facture.getDatefacture());
        factureDB.setClient(facture.getClient());
        factureDB.setProduct(facture.getProduct());
        factureDB.setMontanttc(facture.getMontanttc());
        factureDB.setMontantht(facture.getMontantht());
        Facture updatedFacture = getFactureById(id);

        return updatedFacture;
    }



    @Override
    public void deleteFacture(Long id) {
        Optional<Facture> facture = factureRepository.findById(id);
        facture.ifPresent(f -> {
            factureRepository.delete(f);

        });
    }

    @Override
    public Facture getFactureById(long id) {
        return factureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facture not found"));
    }


}