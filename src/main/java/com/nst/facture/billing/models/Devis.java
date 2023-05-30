package com.nst.facture.billing.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(	name = "devis")
/*,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "numero_devis"),
                @UniqueConstraint(columnNames = "date_devis"),
                @UniqueConstraint(columnNames = "quantity"),
                @UniqueConstraint(columnNames = "price")


        })*/
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
    private Long id;

    @Column(name = "numero_devis")
    private Long numerodevis;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_devis")
    private Date datedevis;

    @ManyToMany
    @JoinColumn(name = "designation")
    private List <Product> product;


    @Column(name = "price")
    private Double price;



}
