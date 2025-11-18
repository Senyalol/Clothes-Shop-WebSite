package com.ClotheShop.CShop.Service.Favorite;

import com.ClotheShop.CShop.Entity.Favorite;

import java.util.List;

public interface FavoriteService {

    List<Favorite> getAllFavorites();

    Favorite getFavoriteById(int id);

    Favorite addFavorite(Favorite favorite);

    Favorite updateFavorite(int id,Favorite favorite);

    void deleteFavoriteById(int id);

}