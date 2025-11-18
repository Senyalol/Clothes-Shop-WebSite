package com.ClotheShop.CShop.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductDTO {

    @JsonProperty("product_id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("availability")
    private Boolean availability;

    @JsonProperty("color")
    private String color;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("sex")
    private String sex;

    @JsonProperty("category")
    private String category;

    @JsonProperty("type")
    private String type;

}