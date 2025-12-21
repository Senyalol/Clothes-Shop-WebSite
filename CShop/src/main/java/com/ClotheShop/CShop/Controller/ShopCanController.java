package com.ClotheShop.CShop.Controller;

import com.ClotheShop.CShop.DTO.ShopCanDTO;
import com.ClotheShop.CShop.Facade.ShopCan.ShopCanFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Добавить метод покупки и оплаты товаров в корзине


@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5174"})
@RestController
@RequestMapping("/api/cans")
public class ShopCanController {

    private final ShopCanFacade shopFacade;

    @Autowired
    public ShopCanController(ShopCanFacade facade) {
        this.shopFacade = facade;
    }

    //Функционал администратора

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

    //Пользовательский функционал

    //Получить мои товары
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @GetMapping("/myCan")
    public List<ShopCanDTO> myShopCan(@RequestHeader("Authorization") String token){
        return shopFacade.getMyShopCan(token);
    }

    //Удалить из корзины моей
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @DeleteMapping("/deleteFromCan/{id}")
    public void deleteFromCan(@PathVariable int id, @RequestHeader("Authorization") String token){
        shopFacade.deleteFromMyShopCan(id, token);
    }


    //Добавить в корзину мою
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @PostMapping
    public ShopCanDTO addShopCan(@RequestHeader("Authorization") String token ,@RequestBody ShopCanDTO shopCanDTO){
        return shopFacade.addToShopCan(token,shopCanDTO);
    }

    //Оплатить товар
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @GetMapping("/paid")
    public List<ShopCanDTO> paidShopCan(@RequestHeader("Authorization") String token){
        return shopFacade.paid(token);
    }

}