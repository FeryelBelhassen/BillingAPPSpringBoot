package com.nst.facture.billing.repository;

import com.nst.facture.billing.models.Devis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevisRepository extends JpaRepository<Devis, Long> {

    List<Devis> findByNumerodevis(Integer numerodevis);


}
