package com.nst.facture.billing;

import com.nst.facture.billing.models.*;
import com.nst.facture.billing.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
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
    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DevisRepository devisRepository;
    @Autowired
    private FactureAvoirRepository factureAvoirRepository;

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

            Role roleAdmin = Role.builder().id(1).name(ERole.ADMIN).build();
            Role role1 = Role.builder().id(2).name(ERole.AGENT).build();
            Role role2 = Role.builder().name(ERole.USER).id(3).build();
            Role role3 = Role.builder().id(4).name(ERole.MAGASINIER).build();
            Role role4 = Role.builder().id(5).name(ERole.CLIENT).build();
            roleRepository.saveAll(List.of(roleAdmin, role1, role2, role3, role4));

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

            User agent = User.builder().username("mohamed")
                    .email("med@gmail.com")
                    .password(passwordEncoder.encode("med123456"))
                    .roles(Collections.singleton(role1))
                    .build();
            userRepository.save(agent);

            User magasinier = User.builder().username("ali")
                    .email("ali@gmail.com")
                    .password(passwordEncoder.encode("ali123456"))
                    .roles(Collections.singleton(role3))
                    .build();
            userRepository.save(magasinier);

            User client1 = User.builder().username("sami")
                    .email("s@gmail.com")
                    .password(passwordEncoder.encode("sami123"))
                    .roles(Collections.singleton(role4))
                    .build();
            userRepository.save(client1);

            Product product = Product.builder().code(12547896L)
                    .designation("Product 1")
                    .quantity(2L)
                    .supplier("ahmed")
                    .price(12L).build();
            productRepository.save(product);

            Product product1 = Product.builder().code(12547886L)
                    .designation("Product 2")
                    .quantity(1L)
                    .supplier("sami")
                    .price(14L).build();
            productRepository.save(product1);


            Client client2= Client.builder().username("sami")
                    .email("sami@gmail.com")
                    .password("sami1478")
                    .adresse("NABEUL")
                    .telephone("52021780").build();
            client2 = clientRepository.save(client2);

            Client client3= Client.builder().username("ali")
                    .email("ali@gmail.com")
                    .password("ali126587")
                    .adresse("TUNIS")
                    .telephone("90236875").build();
            client3 = clientRepository.save(client3);

            Facture facture = Facture.builder().numerofacture(123476L)
                    .client(client2)
                    .datefacture( Date.valueOf("2022-5-23"))
                    .product(List.of(product, product1))
                    .montanttc(120.03)
                    .montantht(20.3).build();
            factureRepository.save(facture);

            Facture facture1 = Facture.builder().numerofacture(123486L)
                    .client(client3)
                    .datefacture( Date.valueOf("2022-7-8"))
                    .product(List.of(product1))
                    .montanttc(220.03)
                    .montantht(30.5).build();
            factureRepository.save(facture1);

            Devis devis= Devis.builder().numerodevis(1236580L)
                    .datedevis(Date.valueOf("2022-5-20"))
                    .quantity(2L)
                    .price(150.23).build();
            devisRepository.save(devis);

            FactureAvoir factureavoir1 = FactureAvoir.builder().numfactureavoir(124476L)
                    .client(client2)
                    .datefacture( Date.valueOf("2022-2-7"))
                    .product(List.of(product))
                    .montanttc(123.03)
                    .montantht(23.3).build();
            factureAvoirRepository.save(factureavoir1);

        };
        }
}
