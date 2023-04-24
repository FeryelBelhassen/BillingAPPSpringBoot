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
import java.time.LocalDate;
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

            Product product = Product.builder().code(12547896L)
                    .designation("Product 1")
                    .quantity(2L)
                    .supplier("ahmed")
                    .price(12L).build();
                    //.status.build();
            productRepository.save(product);

            Client client1= Client.builder().username("sami")
                    .email("sami@gmail.com")
                    .password("sami1478")
                    .adresse("NABEUL")
                    .telephone("52021780").build();
            clientRepository.save(client1);

            Facture facture = Facture.builder().numerofacture(123456L)
                    .client(client1)
                    .datefacture(new Date(2022-02-13))
                   // .productList((List<Product>) product)
                    .montanttc(12.03)
                    .montantht(2.3).build();
            factureRepository.save(facture);

            Devis devis= Devis.builder().numerodevis("1236580")
                    .datedevis(LocalDate.ofEpochDay(2023/04/01))
                    .quantity(2)
                    .price(150.23).build();
            devisRepository.save(devis);

            FactureAvoir factureavoir = FactureAvoir.builder().numfactureavoir(147852)
                   // .client
                    .datefacture(LocalDate.ofEpochDay(2023/05/10))
                    .designation("factureeeeee")
                    .quantity('1')
                    .montanttc(100.32)
                    .montantht(101.3).build();
            factureAvoirRepository.save(factureavoir);

        };
        }
}
