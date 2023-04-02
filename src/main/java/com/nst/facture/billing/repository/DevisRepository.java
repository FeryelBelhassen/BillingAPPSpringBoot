package com.nst.facture.billing.repository;

import com.nst.facture.billing.models.Devis;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DevisRepository extends CrudRepository<Devis, Long> {

    List<Devis> findByNumerodevis(Integer numerodevis);


}
