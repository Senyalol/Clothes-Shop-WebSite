package com.ClotheShop.CShop.DTO.ShopDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserShopDTO {

    @JsonProperty("id")
    private Integer userShopId;

    @JsonProperty("user_id")
    private Integer user_id;

    @JsonProperty("shop_id")
    private Integer shop_id;

}