package com.nst.facture.billing.repository;

import com.nst.facture.billing.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCode(String code);

    Boolean existsByCode(String code);

    Boolean existsByDesignation(String designation);
}
