package com.nst.facture.billing.service;

import com.nst.facture.billing.models.Facture;
import com.nst.facture.billing.models.Product;
import com.nst.facture.billing.models.User;
import com.nst.facture.billing.payload.Dto.FactureDto;
import com.nst.facture.billing.repository.ClientRepository;
import com.nst.facture.billing.repository.FactureRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Facture> getAllFacturesPerUser(Long  idUser) {

        return factureRepository.findAll()
                .stream()
                .filter(facture -> facture.getUtilisateur().getId() == idUser)
                .collect(Collectors.toList());
    }

    @Override
    public List<Facture> getAllFactures() {

        return factureRepository.findAll();
    }
    @Override
    public Facture addFactureFromDTO(FactureDto factureDto){
        Facture toAdd = new Facture();
        toAdd.setUtilisateur((User)SecurityContextHolder.getContext().getAuthentication());
        BeanUtils.copyProperties(factureDto, toAdd);
        return factureRepository.save(toAdd);
    }

    /*double total = product.stream().mapToDouble(Product::getPrice).sum();
     facture.setTotal(total);*/

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

    /*@Override
    public List<Facture> getFacturesByClient(String client) {
        // Utilisez le repository pour récupérer les factures du client spécifié
        List<Facture> factures = factureRepository.findByClient(client);

        // Vous pouvez ajouter d'autres opérations de traitement ou de logique métier si nécessaire

        return factures;
    }*/



}