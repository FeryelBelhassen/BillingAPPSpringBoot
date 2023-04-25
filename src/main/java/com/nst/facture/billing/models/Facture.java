package com.nst.facture.billing.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(	name = "factures",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "numero_facture"),
                //@UniqueConstraint(columnNames = "client_id"),
                //@UniqueConstraint(columnNames = "code"),
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
    private Long id;


    @Column(name = "numero_facture")
    private Long numerofacture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_username")
    private Client client;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_facture")
    private Date datefacture;

    @ManyToMany
    @JoinColumn(name = "designation")
    private List<Product> productList;

    @Column(name = "montant_ttc")
    private double montanttc;

    @Column(name = "montant_ht")
    private double montantht;


}
