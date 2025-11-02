package com.ClotheShop.CShop.Security.SDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserCredentialDTO {

    @JsonProperty("login")
    private String login;

    @JsonProperty("password")
    private String password;

}