package com.nst.facture.billing.repository;

import com.nst.facture.billing.models.Client;
import com.nst.facture.billing.models.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FactureRepository extends JpaRepository<Facture, Long> {

    List<Facture> findByNumerofacture(Integer numerofacture);

    List<Facture> findByClient (Client client);


}
