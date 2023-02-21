package com.nst.facture.billing.repository;

import com.nst.facture.billing.models.ERole;
import com.nst.facture.billing.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(ERole name);
}
