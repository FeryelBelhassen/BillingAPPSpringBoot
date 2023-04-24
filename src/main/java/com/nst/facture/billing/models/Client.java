package com.nst.facture.billing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Getter
@Setter
@Builder

/**
 * This a table of clients
 */
@Table(	name = "clients",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "password"),
                @UniqueConstraint(columnNames = "adresse"),
                @UniqueConstraint(columnNames = "telephone")


        })
/**
 * This class describe entity Client
 */
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 120)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @NotBlank
    @Size(max = 120)
    private String adresse;

    @NotBlank
    @Size(max = 120)
    private String telephone;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Facture> factures = new ArrayList<>();



}
