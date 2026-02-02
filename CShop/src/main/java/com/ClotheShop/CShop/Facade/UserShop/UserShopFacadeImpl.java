package com.ClotheShop.CShop.Facade.UserShop;

import com.ClotheShop.CShop.DTO.ShopDTO.UserShopDTO;
import com.ClotheShop.CShop.Mapper.UserShopMapper;
import com.ClotheShop.CShop.Service.UserShop.UserShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserShopFacadeImpl implements UserShopFacade {

    private final UserShopMapper userShopMapper;
    private final UserShopService userShopService;

    @Autowired
    public UserShopFacadeImpl(UserShopMapper userShopMapper, UserShopService userShopService) {
        this.userShopMapper = userShopMapper;
        this.userShopService = userShopService;
    }

    @Override
    public UserShopDTO createUserShop(String fullToken ,UserShopDTO DTO) {

        String token = getPayLoadToken(fullToken);
        return userShopMapper.toDTO(userShopService.createUserShop(userShopMapper.toEntityWithUser(token,DTO)));
    }

    @Override
    public List<UserShopDTO> getUserShops() {
        return userShopService.getUserShops().stream()
                .map(x -> userShopMapper.toDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserShop(int id) {
        userShopService.deleteUserShop(id);
    }

    @Override
    public void deleteYourShop(int id, String fullToken) {

        String token = getPayLoadToken(fullToken);
        userShopService.deleteYourShop(id, token);
    }

    @Override
    public UserShopDTO updateUserShopById(int id, UserShopDTO DTO) {
        return userShopMapper.toDTO(userShopService.updateUserShopById(id,userShopMapper.toEntity(DTO)));
    }

    //Возможно использовать методы сервиса в сервисе
    @Override
    public UserShopDTO updateYourShop(int id, String fullToken, UserShopDTO DTO) {

        String token = getPayLoadToken(fullToken);
        return userShopMapper.toDTO(userShopService.updateYourShop(id,token,userShopMapper.toEntity(DTO)));
    }

    @Override
    public List<UserShopDTO> getShopsByUser(String fullToken) {

        String token =  getPayLoadToken(fullToken);

        return userShopService.getShopsByUser(token).stream()
                .map(x -> userShopMapper.toDTO(x))
                .collect(Collectors.toList());
    }

    //Получить часть токена с полезной информации
    private String getPayLoadToken(String token){

        String payLoadData = token;

        if(payLoadData != null && payLoadData.startsWith("Bearer ")) {
            payLoadData = payLoadData.substring(7).trim();
        }

        return payLoadData;
    }

}