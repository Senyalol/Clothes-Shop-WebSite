package com.ClotheShop.CShop.Controller;

import com.ClotheShop.CShop.DTO.ShopDTO.UserShopDTO;
import com.ClotheShop.CShop.Facade.UserShop.UserShopFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5174"})
@RestController
@RequestMapping("/api/us")
public class UserShopController {

    private final UserShopFacade userShopFacade;

    @Autowired
    public UserShopController(UserShopFacade userShopFacade) {
        this.userShopFacade = userShopFacade;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<UserShopDTO> getUserShops(){
        return userShopFacade.getUserShops();
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @PostMapping
    UserShopDTO createUserShop(@RequestHeader("Authorization") String token ,@RequestBody UserShopDTO DTO){
        return userShopFacade.createUserShop(token,DTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    void deleteUserShop(@PathVariable int id){
        userShopFacade.deleteUserShop(id);
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @DeleteMapping("/yours/{id}")
    void deleteYourShop(@PathVariable int id,@RequestHeader("Authorization") String token){
        userShopFacade.deleteYourShop(id,token);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    UserShopDTO updateUserShopById(int id,@RequestBody UserShopDTO DTO){
        return userShopFacade.updateUserShopById(id,DTO);
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @PatchMapping("/yours/{id}")
    UserShopDTO updateYourShop(@PathVariable int id,@RequestHeader("Authorization") String token ,@RequestBody UserShopDTO DTO){
        return userShopFacade.updateYourShop(id,token,DTO);
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @GetMapping("yours")
    List<UserShopDTO> getShopsByUser(@RequestHeader("Authorization") String token){
        return userShopFacade.getShopsByUser(token);
    }


}