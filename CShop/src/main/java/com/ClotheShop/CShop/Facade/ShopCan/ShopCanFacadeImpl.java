package com.ClotheShop.CShop.Facade.ShopCan;

import com.ClotheShop.CShop.DTO.ShopCanDTO;
import com.ClotheShop.CShop.Mapper.ShopCanMapper;
import com.ClotheShop.CShop.Service.ShopCan.ShopCanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShopCanFacadeImpl implements ShopCanFacade {

    private final ShopCanMapper shopCanMapper;
    private final ShopCanService shopCanService;

    @Autowired
    public ShopCanFacadeImpl(ShopCanMapper shopCanMapper, ShopCanService shopCanService) {
        this.shopCanMapper = shopCanMapper;
        this.shopCanService = shopCanService;
    }

    @Override
    public List<ShopCanDTO> getAllShopCan() {
        return shopCanService.getAllShopCan()
                .stream()
                .map(x -> shopCanMapper.toDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShopCanDTO> getAllProductsByUserId(int userId) {
        return shopCanService.getAllProductsByUserId(userId)
                .stream()
                .map(x -> shopCanMapper.toDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public ShopCanDTO getShopCanById(int id) {
        return shopCanMapper.toDTO(shopCanService.getShopCanById(id));
    }

    @Override
    public ShopCanDTO addToShopCan(String token , ShopCanDTO dto) {
        return shopCanMapper.toDTO(shopCanService.addToShopCan(shopCanMapper.toEntityWithUser(token,dto)));
    }

    @Override
    public ShopCanDTO updateShopCan(int id, ShopCanDTO dto) {
        return shopCanMapper.toDTO(shopCanService.updateShopCan(id,shopCanMapper.toEntity(dto)));
    }

    @Override
    public void deleteShopCan(int id) {
        shopCanService.deleteShopCan(id);
    }

    @Override
    public List<ShopCanDTO> getMyShopCan(String token) {

        String payLoadData = getPayload(token);

        return shopCanService.getMyShopCan(payLoadData).stream()
                .map(x -> shopCanMapper.toDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFromMyShopCan(int id, String token) {

        String payLoadData = getPayload(token);

        shopCanService.deleteFromMyShopCan(id, payLoadData);

    }

    @Override
    public List<ShopCanDTO> paid(String token) {

        String payLoadData = getPayload(token);

        return shopCanService.paid(payLoadData).stream()
                .map(x -> shopCanMapper.toDTO(x))
                .collect(Collectors.toList());

    }

    private String getPayload(String token) {

        String payLoadData = token;

        if(payLoadData != null && payLoadData.startsWith("Bearer ")) {
            payLoadData = payLoadData.substring(7).trim();
        }


        return payLoadData;
    }

}