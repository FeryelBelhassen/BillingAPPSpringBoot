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
                @UniqueConstraint(columnNames = "montant_ttc"),
                @UniqueConstraint(columnNames = "montant_ht"),



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
    private long numerofacture;

    @Column(name = "id_client")
    private long clientid;

    @Column(name = "date_facture")
    private LocalDate datefacture;

    @Column(name = "montant_ttc")
    private Double montanttc;

    @Column(name = "montant_ht")
    private Double montantht;


}
