package com.nst.facture.billing.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(	name = "factures",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "numero_facture"),
                //@UniqueConstraint(columnNames = "client"),
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "numero_facture")
    private long numerofacture;

    @ManyToOne
    private Client client;

    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_facture")
    private Date datefacture;

    @Column(name = "montant_ttc")
    private Double montanttc;

    @Column(name = "montant_ht")
    private Double montantht;


}
