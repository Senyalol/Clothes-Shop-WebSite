package com.ClotheShop.CShop.Facade.ShopCan;

import com.ClotheShop.CShop.DTO.ShopCanDTO;

import java.util.List;

public interface ShopCanFacade {

    List<ShopCanDTO> getAllShopCan();

    List<ShopCanDTO> getAllProductsByUserId(int userId);

    ShopCanDTO getShopCanById(int id);

    ShopCanDTO addToShopCan(ShopCanDTO dto);

    ShopCanDTO updateShopCan(int id, ShopCanDTO dto);

    void deleteShopCan(int id);


}