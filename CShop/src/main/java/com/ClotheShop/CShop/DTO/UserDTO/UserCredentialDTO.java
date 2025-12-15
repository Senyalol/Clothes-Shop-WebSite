package com.ClotheShop.CShop.DTO.UserDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserCredentialDTO {

    @JsonProperty("login")
    private String login;

    @JsonProperty("password")
    private String password;

}