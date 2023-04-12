package com.nst.facture.billing.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Getter
@Setter
@Builder

/**
 * This a table of products
 */
@Table(	name = "products",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "code"),
                @UniqueConstraint(columnNames = "designation"),
                @UniqueConstraint(columnNames = "quantity"),
                @UniqueConstraint(columnNames = "supplier"),
                @UniqueConstraint(columnNames = "price"),


        })
/**
 * This class describe entity User
 */
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long code;

    @NotBlank
    @Size(max = 120)
    private String designation;


    private Long quantity;

    @NotBlank
    @Size(max = 120)
    private String supplier;

    private Long price;

    @NotBlank
    @Size(max = 120)
    private String status;

}
