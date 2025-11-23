package com.ClotheShop.CShop.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ShopCanDTO {

    @JsonProperty("shop_can_id")
    private Integer ShopID;

    @JsonProperty("user_id")
    private Integer user_id;

    @JsonProperty("product_id")
    private Integer product;

    @JsonProperty("cost")
    private Double cost;

    @JsonProperty("paid")
    private Boolean paid;

}