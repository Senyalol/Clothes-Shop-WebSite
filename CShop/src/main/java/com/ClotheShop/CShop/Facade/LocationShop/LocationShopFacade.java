package com.ClotheShop.CShop.Facade.LocationShop;

import com.ClotheShop.CShop.DTO.LocationShopDTO;

import java.util.List;

public interface LocationShopFacade {

    List<LocationShopDTO> getAllShops();

    LocationShopDTO createShop(LocationShopDTO shopDTO);

    LocationShopDTO updateShop(int id,LocationShopDTO shopDTO);

    void deleteShop(int id);


}