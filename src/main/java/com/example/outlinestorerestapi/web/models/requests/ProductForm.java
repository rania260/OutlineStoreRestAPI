package com.example.outlinestorerestapi.web.models.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductForm {
    private Long id;
    private String code;
    private String name;
    private Double price;
    private Integer quantity;
    private String image;
}
