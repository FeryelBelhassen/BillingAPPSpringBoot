package com.nst.facture.billing.service;

import com.nst.facture.billing.models.FactureAvoir;
import com.nst.facture.billing.payload.Dto.FactureAvoirDto;
import com.nst.facture.billing.repository.FactureAvoirRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
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
    public FactureAvoir addFactureAvoirFromDTO(FactureAvoirDto factureAvoirDto){
        FactureAvoir toAdd = new FactureAvoir();
        BeanUtils.copyProperties(factureAvoirDto, toAdd);
        return factureavoirRepository.save(toAdd);
    }

    @Override
    public FactureAvoir updateFactureAvoir(Long id, FactureAvoir factureAvoir) {
        FactureAvoir factureavoirDB =getFactureAvoirById(id);
        factureavoirDB.setNumfactureavoir(factureAvoir.getNumfactureavoir());
        factureavoirDB.setDatefacture(factureAvoir.getDatefacture());
        factureavoirDB.setClient(factureAvoir.getClient());
        //factureDB.setProductList(facture.getProductList());
        factureavoirDB.setMontanttc(factureAvoir.getMontanttc());
        factureavoirDB.setMontantht(factureAvoir.getMontantht());
        FactureAvoir updatedFactureavoir = getFactureAvoirById(id);

        return updatedFactureavoir;
    }



    @Override
    public void deleteFactureAvoir(Long id) {
        Optional<FactureAvoir> factureAvoir = factureavoirRepository.findById(id);
        factureAvoir.ifPresent(f -> {
            factureavoirRepository.delete(f);

        });
    }

    @Override
    public FactureAvoir getFactureAvoirById(long id) {
        return factureavoirRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FactureAvoir not found"));
    }


}