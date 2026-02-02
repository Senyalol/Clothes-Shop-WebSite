package com.ClotheShop.CShop.Service.UserShop;

import com.ClotheShop.CShop.Entity.UserShop;

import java.util.List;

public interface UserShopService {

    List<UserShop> getUserShops();

    UserShop createUserShop(UserShop userShop);

    void deleteUserShop(int id);

    void deleteYourShop(int id, String token);

    UserShop updateUserShopById(int id, UserShop userShop);

    UserShop updateYourShop(int id, String token ,UserShop userShop);

    List<UserShop> getShopsByUser(String token);

}
