package com.nst.facture.billing.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties("hibernateLazyInitializer")
@Table(	name = "factureavoir")
/*,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "num_factureavoir"),
                //@UniqueConstraint(columnNames = "client"),
                @UniqueConstraint(columnNames = "date_facture"),
                @UniqueConstraint(columnNames = "montant_ttc"),
                @UniqueConstraint(columnNames = "montant_ht"),



        })*/
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
    private Long id;

    @Column(name = "num_factureavoir")
    private Long numfactureavoir;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_facture")
    private Date datefacture;

    @ManyToMany
    @JoinColumn(name = "designation")
    private List <Product> product;


    @Column(name = "montant_ttc")
    private double montanttc;

    @Column(name = "montant_ht")
    private double montantht;


}
