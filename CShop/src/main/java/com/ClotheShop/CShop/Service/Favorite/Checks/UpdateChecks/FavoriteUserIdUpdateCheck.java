package com.ClotheShop.CShop.Service.Favorite.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Favorite;
import com.ClotheShop.CShop.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FavoriteUserIdUpdateCheck implements FavoriteUpdateCheck{

    private static Logger LOGGER = LogManager.getLogger(FavoriteUserIdUpdateCheck.class);
    private UserRepository userRepository;

    public FavoriteUserIdUpdateCheck(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void check(Favorite oldFav, Favorite newFav) {

        if(newFav != null && newFav.getUser() != null && userRepository.findById(newFav.getUser().getId()).isPresent()){

            oldFav.setUser(newFav.getUser());
            Integer oldUserId = oldFav.getUser().getId();
            LOGGER.info("{} - Favorite user was updated from - {} to - {}",oldFav.getId(),oldUserId,oldFav.getUser().getId());
        }
        else{
            oldFav.setUser(oldFav.getUser());
        }

    }

}