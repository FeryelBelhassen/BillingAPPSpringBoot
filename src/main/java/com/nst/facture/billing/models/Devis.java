package com.nst.facture.billing.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(	name = "factures",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "numero_facture"),
                @UniqueConstraint(columnNames = "id_client"),
                @UniqueConstraint(columnNames = "date_facture"),
                @UniqueConstraint(columnNames = "date_validation"),
                @UniqueConstraint(columnNames = "montant_ttc"),
                @UniqueConstraint(columnNames = "montant_ht"),
                @UniqueConstraint(columnNames = "annee"),


        })
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

/**
 * This class describes Facture Entity
 */

public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "numero_facture")
    private String numero;

    @Column(name = "id_client")
    private String ClientId;

    @Column(name = "date_facture")
    private LocalDate dateFacture;

    @Column(name = "date_validation")
    private LocalDate dateValidation;

    @Column(name = "montant_ttc")
    private Double montantTTC;

    @Column(name = "montant_ht")
    private Double montantHT;

    @Column(name = "annee")
    private int annee;



}
