package com.marketcam.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDTO {

    private Long id;
    private String name;
    private Double price;
    private String category;
    private Integer quantity;

}
