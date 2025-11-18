package com.ClotheShop.CShop.Service.Favorite.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Favorite;

import java.util.List;

public class MainFavoriteCreateCheck {

    private List<FavoriteCreateCheck> favoriteCreateChecks;

    public MainFavoriteCreateCheck(List<FavoriteCreateCheck> favoriteCreateChecks) {
        this.favoriteCreateChecks = favoriteCreateChecks;
    }

    public boolean createCheck(Favorite favorite) {

        boolean result = true;

        for(FavoriteCreateCheck check : favoriteCreateChecks) {

            if(!check.check(favorite)) {
                result = false;
            }

        }

        return result;
    }

}