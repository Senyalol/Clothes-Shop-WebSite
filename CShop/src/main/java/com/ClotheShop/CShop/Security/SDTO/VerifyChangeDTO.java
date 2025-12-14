package com.ClotheShop.CShop.Security.SDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VerifyChangeDTO {

    @JsonProperty("login")
    private String login;

    @JsonProperty("password")
    private String password;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("balance")
    private Double balance;

    @JsonProperty("secretKey")
    private String secretKey;

    @JsonProperty("oldPassword")
    private String oldPassword;

}