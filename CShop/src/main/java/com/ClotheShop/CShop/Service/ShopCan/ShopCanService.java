package com.ClotheShop.CShop.Service.ShopCan;

import com.ClotheShop.CShop.Entity.ShopCan;

import java.util.List;

public interface ShopCanService {

    List<ShopCan> getAllShopCan();

    List<ShopCan> getAllProductsByUserId(int userId);

    ShopCan getShopCanById(int id);

    ShopCan addToShopCan(ShopCan shopCan);

    ShopCan updateShopCan(int id, ShopCan shopCan);

    void deleteShopCan(int id);

    List<ShopCan> getMyShopCan(String token);

    void deleteFromMyShopCan(int id, String token);

    List<ShopCan> paid(String token);

}