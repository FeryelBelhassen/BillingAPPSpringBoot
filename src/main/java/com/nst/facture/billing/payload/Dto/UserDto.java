package com.nst.facture.billing.payload.Dto;

import com.nst.facture.billing.models.Role;
import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String password;
    private Set<Role> roles;

    public void setId(int id) {
    }
}
