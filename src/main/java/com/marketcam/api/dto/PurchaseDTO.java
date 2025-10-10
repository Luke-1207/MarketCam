package com.marketcam.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {

    private Long id;
    private List<ProductDTO> products;
    private Double totalPrice;
    private LocalDateTime purchaseDate;

}
