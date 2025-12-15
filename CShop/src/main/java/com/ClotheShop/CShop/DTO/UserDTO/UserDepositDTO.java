package com.ClotheShop.CShop.DTO.UserDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDepositDTO {

    @JsonProperty("deposit")
    private Double deposit;

}