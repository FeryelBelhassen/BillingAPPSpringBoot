package com.nst.facture.billing.repository;

import com.nst.facture.billing.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    public default void deleteAll() {

        for (User user : findAll()) {
            delete(user);
        }
    }
}
