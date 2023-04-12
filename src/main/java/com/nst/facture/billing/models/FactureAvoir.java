package com.nst.facture.billing.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(	name = "factureavoir",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "num_factureavoir"),
                //@UniqueConstraint(columnNames = "client"),
                @UniqueConstraint(columnNames = "date_facture"),
                @UniqueConstraint(columnNames = "designation"),
                @UniqueConstraint(columnNames = "quantity"),
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
 * This class describes FactureAvoir Entity
 */

public class FactureAvoir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idfactavoir;

    @Column(name = "num_factureavoir")
    private long numfactureavoir;

    @ManyToOne
    @JoinColumn(name = "id_client", insertable = false, updatable = false)
    private Client client;

    @Column(name = "date_facture")
    private LocalDate datefacture;

    @Column(name = "designation")
    private String designation;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "montant_ttc")
    private Double montanttc;

    @Column(name = "montant_ht")
    private Double montantht;


}
