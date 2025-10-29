package com.ClotheShop.CShop.Controller;

import com.ClotheShop.CShop.DTO.ProductDTO;
import com.ClotheShop.CShop.Facade.Product.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductFacade productFacade;
    
    @Autowired
    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

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

    @PatchMapping("/update/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO product,@PathVariable int id){
        return productFacade.updateProduct(product, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable int id){
        productFacade.deleteProduct(id);
    }


}