package com.ClotheShop.CShop.Facade.LocationShop;

import com.ClotheShop.CShop.DTO.LocationShopDTO;
import com.ClotheShop.CShop.Mapper.LocationShopMapper;
import com.ClotheShop.CShop.Service.LocationShop.LocationShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationShopFacadeImpl implements LocationShopFacade {

    private final LocationShopMapper locationShopMapper;
    private final LocationShopService locationShopService;

    @Autowired
    public LocationShopFacadeImpl(LocationShopMapper locationShopMapper, LocationShopService locationShopService) {
        this.locationShopMapper = locationShopMapper;
        this.locationShopService = locationShopService;
    }

    @Override
    public List<LocationShopDTO> getAllShops() {
        return locationShopService.getAllShops().stream()
                .map(x -> locationShopMapper.toDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public LocationShopDTO createShop(LocationShopDTO shopDTO) {
        return locationShopMapper.toDTO(locationShopService.createShop(locationShopMapper.toEntity(shopDTO)));
    }

    @Override
    public LocationShopDTO updateShop(int id, LocationShopDTO shopDTO) {
        return locationShopMapper.toDTO(locationShopService.updateShop(id, locationShopMapper.toEntity(shopDTO)));
    }

    @Override
    public void deleteShop(int id) {
        locationShopService.deleteShop(id);
    }
}