package com.nst.facture.billing.payload.Dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long code;
    private String designation;
    private Long quantity;
    private String supplier;
    private Long price;
    //private List status;
}
