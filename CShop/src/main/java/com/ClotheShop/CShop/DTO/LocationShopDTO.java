package com.ClotheShop.CShop.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalTime;

@Data
public class LocationShopDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("location")
    private String location;

    @JsonProperty("rating")
    private Integer rating;

    @JsonProperty("opening")
    private LocalTime opening;

    @JsonProperty("closing")
    private LocalTime closing;

}