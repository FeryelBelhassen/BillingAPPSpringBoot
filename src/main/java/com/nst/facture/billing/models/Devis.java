package com.nst.facture.billing.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(	name = "devis",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "numero_devis"),
                @UniqueConstraint(columnNames = "date_devis"),
                @UniqueConstraint(columnNames = "quantity"),
                @UniqueConstraint(columnNames = "price")


        })
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

/**
 * This class describes Devis Entity
 */
public class Devis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "numero_devis")
    private String numerodevis;

    @Column(name = "date_devis")
    private LocalDate datedevis;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private Double price;




}
