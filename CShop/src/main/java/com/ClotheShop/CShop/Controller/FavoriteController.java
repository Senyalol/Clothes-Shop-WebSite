package com.ClotheShop.CShop.Controller;

import com.ClotheShop.CShop.DTO.FavoriteDTO;
import com.ClotheShop.CShop.Facade.Favorite.FavoriteFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Нужна хорошая доработка ролей

//Мое избранное

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5174"})
@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteFacade favoriteFacade;

    @Autowired
    public FavoriteController(FavoriteFacade favoriteFacade) {
        this.favoriteFacade = favoriteFacade;
    }

    //Админский функционал

    //Получить все избранные
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<FavoriteDTO> getFavorites() {
        return favoriteFacade.getAllFavorites();
    }

    //Получить конкретное избранное
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public FavoriteDTO getFavoriteById(@PathVariable int id) {
        return favoriteFacade.getFavoriteById(id);
    }

    //Обновить конкретное избранное
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/update/{id}")
    public FavoriteDTO updateFavorite(@PathVariable int id, @RequestBody FavoriteDTO favoriteDTO) {
        return favoriteFacade.updateFavorite(id, favoriteDTO);
    }

    //Удалить конкретное избранное
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteFavorite(@PathVariable int id) {
        favoriteFacade.deleteFavoriteById(id);
    }

    //Пользовательский функционал

    //Добавить в избранное
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @PostMapping
    public FavoriteDTO addFavorite(@RequestHeader("Authorization") String token ,@RequestBody FavoriteDTO favoriteDTO) {
        return favoriteFacade.addFavorite(token,favoriteDTO);
    }

    //Получить все избранные
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @GetMapping("/myFav")
    public List<FavoriteDTO> getMyFavorites(@RequestHeader("Authorization") String token) {
        return favoriteFacade.getFavoritesByToken(token);
    }

    //Удалить из избранного
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @DeleteMapping("/delFavorite/{id}")
    public void deltFavorite(@PathVariable int id, @RequestHeader("Authorization") String token, @RequestBody FavoriteDTO favoriteDTO) {
           favoriteFacade.deleteFromFavorite(id, token, favoriteDTO);
    }

}