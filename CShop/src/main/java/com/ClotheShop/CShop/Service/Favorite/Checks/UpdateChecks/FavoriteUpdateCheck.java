package com.ClotheShop.CShop.Service.Favorite.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Favorite;

public interface FavoriteUpdateCheck {

    void check(Favorite oldFav, Favorite newFav);

}