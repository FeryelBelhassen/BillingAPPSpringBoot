package com.nst.facture.billing.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

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
    private Long idfactavoir;

    @Column(name = "num_factureavoir")
    private Long numfactureavoir;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_username")
    private Client client;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_facture")
    private Date datefacture;


    @Column(name = "designation")
    private String designation;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "montant_ttc")
    private Double montanttc;

    @Column(name = "montant_ht")
    private Double montantht;


}
