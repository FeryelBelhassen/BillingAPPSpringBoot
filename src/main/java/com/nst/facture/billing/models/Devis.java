package com.nst.facture.billing.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "numero_devis")
    private String numerodevis;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date_devis")
    private Date datedevis;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "price")
    private Double price;




}
