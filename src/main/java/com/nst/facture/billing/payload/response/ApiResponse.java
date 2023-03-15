package com.nst.facture.billing.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
    public ApiResponse(Boolean aTrue, String user_deleted_successfully, HttpStatus ok) {
    }
}
