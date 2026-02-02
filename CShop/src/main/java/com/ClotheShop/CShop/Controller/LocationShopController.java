package com.ClotheShop.CShop.Controller;

import com.ClotheShop.CShop.DTO.ShopDTO.LocationShopDTO;
import com.ClotheShop.CShop.Facade.LocationShop.LocationShopFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5174"})
@RestController
@RequestMapping("/api/shops")
public class LocationShopController {

    private final LocationShopFacade facade;

    @Autowired
    public LocationShopController(LocationShopFacade facade) {
        this.facade = facade;
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @GetMapping
    public List<LocationShopDTO> getAllLocationShops() {
        return facade.getAllShops();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public LocationShopDTO createLocationShop(@RequestBody LocationShopDTO locationShopDTO) {
        return facade.createShop(locationShopDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteLocationShop(@PathVariable int id) {
        facade.deleteShop(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/update/{id}")
    public LocationShopDTO updateLocationShop(@PathVariable int id, @RequestBody LocationShopDTO locationShopDTO) {
        return facade.updateShop(id, locationShopDTO);
    }

}