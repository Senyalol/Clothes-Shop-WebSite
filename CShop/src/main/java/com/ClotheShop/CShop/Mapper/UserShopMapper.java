package com.ClotheShop.CShop.Mapper;

import com.ClotheShop.CShop.DTO.ShopDTO.UserShopDTO;
import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Entity.UserShop;
import com.ClotheShop.CShop.Repository.LocationShopRepository;
import com.ClotheShop.CShop.Repository.UserRepository;
import com.ClotheShop.CShop.Security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserShopMapper {

    private final UserRepository userRepository;
    private final LocationShopRepository locationShopRepository;
    private final JWTService jwtService;

    @Autowired
    public UserShopMapper(UserRepository userRepository, LocationShopRepository locationShopRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.locationShopRepository = locationShopRepository;
        this.jwtService = jwtService;
    }

    //Из DTO в сущность
    public UserShop toEntity(UserShopDTO userShopDTO) {

        UserShop usershop = new UserShop();

        if(userShopDTO.getShop_id() != null && userShopDTO.getUser_id() != null) {

            usershop.setUser(userRepository.findById(userShopDTO.getUser_id()).get());
            usershop.setShop(locationShopRepository.findById(userShopDTO.getShop_id()).get());
            return usershop;
        }

        return null;
    }

    //Из сущности в DTO
    public UserShopDTO toDTO(UserShop usershop) {

        UserShopDTO userShopDTO = new UserShopDTO();
        userShopDTO.setUserShopId(usershop.getId());
        userShopDTO.setUser_id(usershop.getUser().getId());
        userShopDTO.setShop_id(usershop.getShop().getId());

        return userShopDTO;
    }

    //В сущность с известным пользователем
    public UserShop toEntityWithUser(String token , UserShopDTO userShopDTO) {

        UserShop usershop = new UserShop();

        User yourself = userRepository.findByLogin(jwtService.getLoginFromToken(token)).get();
        usershop.setUser(yourself);
        usershop.setShop(locationShopRepository.findById(userShopDTO.getShop_id()).get());

        return usershop;
    }

}