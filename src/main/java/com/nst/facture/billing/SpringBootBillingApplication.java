package com.nst.facture.billing;

import com.nst.facture.billing.models.User;
import com.nst.facture.billing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringBootBillingApplication {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBillingApplication.class, args);
    }

    @Bean
    ApplicationRunner appStarted() {
        return args -> {
            User admin = User.builder().username("feryel")
                    .email("dd@dd.de")
                    .password(passwordEncoder.encode("123"))
                    .build();
            userRepository.save(admin);};
    }

}
