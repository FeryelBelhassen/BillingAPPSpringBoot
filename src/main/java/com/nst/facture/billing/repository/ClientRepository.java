package com.nst.facture.billing.repository;

import com.nst.facture.billing.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByAdresse(String adresse);
}
