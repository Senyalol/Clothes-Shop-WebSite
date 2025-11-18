package com.ClotheShop.CShop.Facade.Favorite;

import com.ClotheShop.CShop.DTO.FavoriteDTO;
import com.ClotheShop.CShop.Mapper.FavoriteMapper;
import com.ClotheShop.CShop.Service.Favorite.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FavoriteFacadeImpl implements FavoriteFacade {

    private final FavoriteMapper favoriteMapper;
    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteFacadeImpl(FavoriteMapper favoriteMapper,FavoriteService favoriteService) {
        this.favoriteMapper = favoriteMapper;
        this.favoriteService = favoriteService;
    }

    @Override
    public FavoriteDTO addFavorite(FavoriteDTO favoriteDTO) {
        return favoriteMapper.toDTO(favoriteService.addFavorite(favoriteMapper.toEntity(favoriteDTO)));
    }

    @Override
    public List<FavoriteDTO> getAllFavorites() {
        return favoriteService.getAllFavorites()
                .stream()
                .map(x-> favoriteMapper.toDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public FavoriteDTO getFavoriteById(int id) {
        return favoriteMapper.toDTO(favoriteService.getFavoriteById(id));
    }

    @Override
    public FavoriteDTO updateFavorite(int id, FavoriteDTO favoriteDTO) {
        return favoriteMapper.toDTO(favoriteService.updateFavorite(id,favoriteMapper.toEntity(favoriteDTO)));
    }

    @Override
    public void deleteFavoriteById(int id) {
        favoriteService.deleteFavoriteById(id);
    }

}