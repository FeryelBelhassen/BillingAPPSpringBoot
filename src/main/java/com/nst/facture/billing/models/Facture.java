package com.nst.facture.billing.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    private Integer numero;

    @Column(name = "id_client")
    private String ClientId;

    @Column(name = "date_facture")
    private Date dateFacture;

    @Column(name = "date_validation")
    private Date dateValidation;

    @Column(name = "montant_ttc")
    private Float montantTTC;

    @Column(name = "montant_ht")
    private Float montantHT;

    @Column(name = "annee")
    private Float annee;



}
