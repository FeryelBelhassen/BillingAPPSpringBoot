package com.nst.facture.billing.repository;

import com.nst.facture.billing.models.Facture;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FactureRepository extends CrudRepository<Facture, Long> {

    List<Facture> findByNumero(Integer numero);


}
