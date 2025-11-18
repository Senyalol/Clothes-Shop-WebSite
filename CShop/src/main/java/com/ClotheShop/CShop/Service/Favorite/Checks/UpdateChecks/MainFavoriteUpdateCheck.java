package com.ClotheShop.CShop.Service.Favorite.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Favorite;
import java.util.List;

public class MainFavoriteUpdateCheck {

    private List<FavoriteUpdateCheck> favoriteUpdateChecks;

    public MainFavoriteUpdateCheck(List<FavoriteUpdateCheck> favoriteUpdateChecks) {
        this.favoriteUpdateChecks = favoriteUpdateChecks;
    }

    public void updateChecks(Favorite oldFav, Favorite newFav) {

        for (FavoriteUpdateCheck updateCheck : favoriteUpdateChecks) {

            updateCheck.check(oldFav, newFav);

        }

    }

}