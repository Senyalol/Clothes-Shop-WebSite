package com.ClotheShop.CShop.Facade.Favorite;

import com.ClotheShop.CShop.DTO.FavoriteDTO;
import java.util.List;

public interface FavoriteFacade {

    List<FavoriteDTO> getAllFavorites();

    FavoriteDTO getFavoriteById(int id);

    FavoriteDTO addFavorite(FavoriteDTO favoriteDTO);

    FavoriteDTO updateFavorite(int id,FavoriteDTO favoriteDTO);

    void deleteFavoriteById(int id);

}
