package com.nst.facture.billing.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name= "roles")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

/**
 * This class describes Role entity
 */
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

}
