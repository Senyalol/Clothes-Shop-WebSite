package com.ClotheShop.CShop.Controller;

import com.ClotheShop.CShop.DTO.FavoriteDTO;
import com.ClotheShop.CShop.Facade.Favorite.FavoriteFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Нужна хорошая доработка ролей

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5174"})
@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteFacade favoriteFacade;

    @Autowired
    public FavoriteController(FavoriteFacade favoriteFacade) {
        this.favoriteFacade = favoriteFacade;
    }

    //Получить все избранные
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @GetMapping
    public List<FavoriteDTO> getFavorites() {
        return favoriteFacade.getAllFavorites();
    }

    //Получить конкретное избранное
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @GetMapping("/{id}")
    public FavoriteDTO getFavoriteById(@PathVariable int id) {
        return favoriteFacade.getFavoriteById(id);
    }

    //Добавить в избранное
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @PostMapping
    public FavoriteDTO addFavorite(@RequestBody FavoriteDTO favoriteDTO) {
        return favoriteFacade.addFavorite(favoriteDTO);
    }

    //Обновить конкретное избранное
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @PatchMapping("/update/{id}")
    public FavoriteDTO updateFavorite(@PathVariable int id, @RequestBody FavoriteDTO favoriteDTO) {
        return favoriteFacade.updateFavorite(id, favoriteDTO);
    }

    //Удалить конкретное избранное
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @DeleteMapping("/delete/{id}")
    public void deleteFavorite(@PathVariable int id) {
        favoriteFacade.deleteFavoriteById(id);
    }

}