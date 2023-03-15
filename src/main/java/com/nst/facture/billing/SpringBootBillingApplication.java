package com.nst.facture.billing;

import com.nst.facture.billing.models.ERole;
import com.nst.facture.billing.models.Product;
import com.nst.facture.billing.models.Role;
import com.nst.facture.billing.models.User;
import com.nst.facture.billing.repository.ProductRepository;
import com.nst.facture.billing.repository.RoleRepository;
import com.nst.facture.billing.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.nst.facture.billing.controllers"})
public class SpringBootBillingApplication {


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private ProductRepository productRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBillingApplication.class, args);
        System.out.println("hello");
    }

    @Bean
    ApplicationRunner appStarted() {
        return args -> {

            Role roleAdmin = Role.builder().id(1).name(ERole.ROLE_ADMIN).build();
            Role role1 = Role.builder().id(2).name(ERole.ROLE_AGENT).build();
            Role role2 = Role.builder().name(ERole.ROLE_USER).id(3).build();
            Role role3 = Role.builder().id(4).name(ERole.ROLE_MAGASINIER).build();
            Role role4 = Role.builder().id(5).name(ERole.ROLE_CLIENT).build();
            roleRepository.saveAll(List.of(roleAdmin, role1, role2, role3, role4));
            /*Facture facture = Facture.builder().numero(1).nomclient("Feryel").date(01/02/2021)
                    .designation("f").quantite(2).tauxtva(12).tauxttc(15).montantht(170).build();
            factureRepository.saveAll(facture);*/

            User admin = User.builder().username("feryel")
                    .email("feryel@gmail.com")
                    .password(passwordEncoder.encode("12345678"))
                    .roles(Collections.singleton(roleAdmin))
                    .build();
            userRepository.save(admin);

            User client = User.builder().username("ahmed")
                    .email("a@gmail.com")
                    .password(passwordEncoder.encode("ahmed123"))
                    .roles(Collections.singleton(role4))
                    .build();
            userRepository.save(client);

            Product product = Product.builder().code("12345")
                    .designation("Product 1")
                    .quantity("2")
                    .supplier("ahmed")
                    .price("12").build();
            productRepository.save(product);
/*
            User agent = User.builder().username("ahmed")
                    .email("ahmed@gmail.com")
                    .password(passwordEncoder.encode("a12345678"))
                    .roles(Collections.singleton(role1))
                    .build();
            userRepository.save(agent);*/


    };
    }

}
