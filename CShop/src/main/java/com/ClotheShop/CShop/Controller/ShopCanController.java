package com.ClotheShop.CShop.Controller;

import com.ClotheShop.CShop.DTO.ShopCanDTO;
import com.ClotheShop.CShop.Facade.ShopCan.ShopCanFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Добавить метод покупки и оплаты товаров в корзине
//Добавить корзину пользователя

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5174"})
@RestController
@RequestMapping("/api/cans")
public class ShopCanController {

    private final ShopCanFacade shopFacade;

    @Autowired
    public ShopCanController(ShopCanFacade facade) {
        this.shopFacade = facade;
    }

    //Получить все корзины
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<ShopCanDTO> getAllCans(){
        return shopFacade.getAllShopCan();
    }

    //Получить все товары пользователя
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/productsU/{id}")
    public List<ShopCanDTO> getAllProductsU(@PathVariable int id){
        return shopFacade.getAllProductsByUserId(id);
    }

    //Получить конкретную корзину
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ShopCanDTO findById(@PathVariable int id){
        return shopFacade.getShopCanById(id);
    }

    //Добавить товар в корзину
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @PostMapping
    public ShopCanDTO addShopCan(@RequestBody ShopCanDTO shopCanDTO){
        return shopFacade.addToShopCan(shopCanDTO);
    }

    //Обновить товар в корзине
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/update/{id}")
    public ShopCanDTO updateShopCan(@PathVariable int id, @RequestBody ShopCanDTO shopCanDTO){
        return shopFacade.updateShopCan(id, shopCanDTO);
    }

    //Удалить товар из корзины пользователя
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteShopCan(@PathVariable int id){
        shopFacade.deleteShopCan(id);
    }

}