package com.nst.facture.billing.payload.Dto;

import com.nst.facture.billing.models.Facture;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private String username;
    private String email;
    private String password;
    private String adresse;
    private String telephone;
    private Facture facture;
}
