package com.ClotheShop.CShop.Service.LocationShop;

import com.ClotheShop.CShop.Entity.Locationshop;

import java.util.List;

public interface LocationShopService {

    List<Locationshop> getAllShops();

    Locationshop createShop(Locationshop shop);

    Locationshop updateShop(int id,Locationshop shop);

    void deleteShop(int id);

}