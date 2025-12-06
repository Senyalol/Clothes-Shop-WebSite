package com.ClotheShop.CShop.Controller;

import com.ClotheShop.CShop.DTO.ProductDTO;
import com.ClotheShop.CShop.Facade.Product.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5174"})
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductFacade productFacade;
    
    @Autowired
    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO product){
        return productFacade.addProduct(product);
    }

    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productFacade.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getCertainProduct(@PathVariable int id){
        return productFacade.getCertainProduct(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/update/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO product,@PathVariable int id){
        return productFacade.updateProduct(product, id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable int id){
        productFacade.deleteProduct(id);
    }

}
//@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")