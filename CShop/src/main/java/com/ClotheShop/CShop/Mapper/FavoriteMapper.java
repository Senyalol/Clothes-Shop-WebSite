package com.ClotheShop.CShop.Mapper;

import com.ClotheShop.CShop.DTO.FavoriteDTO;
import com.ClotheShop.CShop.Entity.Favorite;
import com.ClotheShop.CShop.Repository.ProductRepository;
import com.ClotheShop.CShop.Repository.UserRepository;
import com.ClotheShop.CShop.Security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FavoriteMapper {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private final JWTService jwtService;

    @Autowired
    public FavoriteMapper(UserRepository userRepository, ProductRepository productRepository,JWTService jwtService) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.jwtService = jwtService;
    }

    //Из DTO в сущность
    public Favorite toEntity(FavoriteDTO favoriteDTO){

        Favorite favorite = new Favorite();

        favorite.setUser(userRepository.findById(favoriteDTO.getUserId()).get());
        favorite.setProduct(productRepository.findById(favoriteDTO.getProductId()).get());

        return favorite;
    }

    public Favorite toEntityWithUser(String token,FavoriteDTO favoriteDTO){

        Favorite favorite = new Favorite();

        String payLoadData = token;

        if(payLoadData != null && payLoadData.startsWith("Bearer ")) {
            payLoadData = payLoadData.substring(7).trim();
        }

        favorite.setUser(userRepository.findByLogin(jwtService.getLoginFromToken(payLoadData)).get());
        favorite.setProduct(productRepository.findById(favoriteDTO.getProductId()).get());

        return favorite;
    }

    //Из сущности в DTO
    public FavoriteDTO toDTO(Favorite favorite){

        FavoriteDTO dto = new FavoriteDTO();

        dto.setFavoriteId(favorite.getId());
        dto.setUserId(favorite.getUser().getId());
        dto.setProductId(favorite.getProduct().getId());
        return dto;
    }

}