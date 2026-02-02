package com.ClotheShop.CShop.Facade.UserShop;

import com.ClotheShop.CShop.DTO.ShopDTO.UserShopDTO;

import java.util.List;

public interface UserShopFacade {

    List<UserShopDTO> getUserShops();

    UserShopDTO createUserShop(String token , UserShopDTO DTO);

    void deleteUserShop(int id);

    void deleteYourShop(int id, String token);

    UserShopDTO updateUserShopById(int id, UserShopDTO DTO);

    UserShopDTO updateYourShop(int id, String token ,UserShopDTO DTO);

    List<UserShopDTO> getShopsByUser(String token);

}
