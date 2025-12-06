package com.ClotheShop.CShop.Controller;

import com.ClotheShop.CShop.DTO.ProductImageDTO;
import com.ClotheShop.CShop.Facade.ProductImage.ProductImageFacade;
import com.ClotheShop.CShop.Facade.ProductImage.ProductImageFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5174"})
@RestController
@RequestMapping("/api/images")
public class ProductImageController {

    private final ProductImageFacade productImageFacade;

    @Autowired
    public ProductImageController(ProductImageFacade productImageFacade, ProductImageFacadeImpl productImageFacadeImpl) {
        this.productImageFacade = productImageFacade;
    }

    //Получить все фотографии
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @GetMapping
    public List<ProductImageDTO> getProductImages() {
        return productImageFacade.getAllProductImages();
    }

    //Получить конкретную фотографию
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @GetMapping("/{id}")
    public ProductImageDTO getProductImage(@PathVariable int id) {
        return productImageFacade.getProductImageById(id);
    }

    //Добавить фотографию
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ProductImageDTO addProductImage(@RequestBody ProductImageDTO productImageDTO) {
        return productImageFacade.addProductImage(productImageDTO);
    }

    //Обновить фотографию
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/update/{id}")
    public ProductImageDTO updateProductImage(@PathVariable int id, @RequestBody ProductImageDTO productImageDTO) {
        return productImageFacade.updateProductImage(id, productImageDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteProductImage(@PathVariable int id) {
        productImageFacade.deleteById(id);
    }

}