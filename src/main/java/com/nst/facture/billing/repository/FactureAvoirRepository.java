package com.nst.facture.billing.repository;

import com.nst.facture.billing.models.Facture;
import com.nst.facture.billing.models.FactureAvoir;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FactureAvoirRepository extends CrudRepository<FactureAvoir, Long> {

    List<Facture> findByNumfactureavoir(Integer numfactureavoir);


}
