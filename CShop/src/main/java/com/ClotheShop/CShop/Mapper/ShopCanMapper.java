package com.ClotheShop.CShop.Mapper;

import com.ClotheShop.CShop.DTO.ShopCanDTO;
import com.ClotheShop.CShop.Entity.ShopCan;
import com.ClotheShop.CShop.Repository.ProductRepository;
import com.ClotheShop.CShop.Repository.UserRepository;
import com.ClotheShop.CShop.Security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShopCanMapper {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private final JWTService jwtService;

    @Autowired
    public ShopCanMapper(UserRepository userRepository, ProductRepository productRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.jwtService = jwtService;
    }

    //Из DTO в сущность
    public ShopCan toEntity(ShopCanDTO dto){

        ShopCan shopCan = new ShopCan();

        shopCan.setUser(userRepository.findById(dto.getUser_id()).get());
        shopCan.setProduct(productRepository.findById(dto.getProduct()).get());
        shopCan.setCost(dto.getCost());
        shopCan.setPaid(dto.getPaid());


        return shopCan;
    }

    public ShopCan toEntityWithUser(String token ,ShopCanDTO dto){

        ShopCan shopCan = new ShopCan();

        String payLoadData = token;

        if(payLoadData != null && payLoadData.startsWith("Bearer ")) {
            payLoadData = payLoadData.substring(7).trim();
        }

        shopCan.setUser(userRepository.findByLogin(jwtService.getLoginFromToken(payLoadData)).get());
        shopCan.setProduct(productRepository.findById(dto.getProduct()).get());
        shopCan.setCost(dto.getCost());
        shopCan.setPaid(dto.getPaid());


        return shopCan;
    }

    //Из сущности в DTO
    public ShopCanDTO toDTO(ShopCan shopCan){

        ShopCanDTO shopCanDTO = new ShopCanDTO();

        shopCanDTO.setShopID(shopCan.getId());
        shopCanDTO.setUser_id(shopCan.getUser().getId());
        shopCanDTO.setProduct(shopCan.getProduct().getId());
        shopCanDTO.setCost(shopCan.getCost());
        shopCanDTO.setPaid(shopCan.getPaid());

        return shopCanDTO;
    }

}