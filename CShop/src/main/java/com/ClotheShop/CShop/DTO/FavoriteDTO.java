package com.ClotheShop.CShop.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FavoriteDTO {

    @JsonProperty("favorite_id")
    private Integer favoriteId;

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("product_id")
    private Integer productId;

}