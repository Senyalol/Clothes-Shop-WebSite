package com.ClotheShop.CShop.Mapper;

import com.ClotheShop.CShop.DTO.ShopDTO.LocationShopDTO;
import com.ClotheShop.CShop.Entity.Locationshop;
import org.springframework.stereotype.Component;

@Component
public class LocationShopMapper {

    //Из DTO в сущность
    public Locationshop toEntity(LocationShopDTO locationShopDTO) {

        Locationshop locationshop = new Locationshop();

        locationshop.setLocation(locationShopDTO.getLocation());
        locationshop.setRating(locationShopDTO.getRating());
        locationshop.setOpening(locationShopDTO.getOpening());
        locationshop.setClosing(locationShopDTO.getClosing());

        return locationshop;
    }

    //Из сущности в DTO
    public LocationShopDTO toDTO(Locationshop locationshop) {

        LocationShopDTO locationShopDTO = new LocationShopDTO();

        locationShopDTO.setId(locationshop.getId());
        locationShopDTO.setLocation(locationshop.getLocation());
        locationShopDTO.setRating(locationshop.getRating());
        locationShopDTO.setOpening(locationshop.getOpening());
        locationShopDTO.setClosing(locationshop.getClosing());

        return locationShopDTO;
    }

}