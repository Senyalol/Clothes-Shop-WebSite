package com.ClotheShop.CShop.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductImageDTO {

    @JsonProperty("product_id")
    private Integer productImageId;

    @JsonProperty("product")
    private Integer product;

    @JsonProperty("image")
    private String image;

}