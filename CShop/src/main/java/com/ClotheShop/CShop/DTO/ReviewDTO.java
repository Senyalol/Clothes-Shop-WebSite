package com.ClotheShop.CShop.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReviewDTO{

    @JsonProperty("review_id")
    private Integer id;

    @JsonProperty("product_id")
    private Integer product;

    @JsonProperty("user_id")
    private Integer user;

    @JsonProperty("review")
    private String review;

}