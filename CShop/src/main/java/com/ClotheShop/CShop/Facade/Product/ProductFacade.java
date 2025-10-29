package com.ClotheShop.CShop.Facade.Product;

import com.ClotheShop.CShop.DTO.ProductDTO;

import java.util.List;

public interface ProductFacade {

    ProductDTO addProduct(ProductDTO product);

    List<ProductDTO> getAllProducts();

    ProductDTO getCertainProduct(int id);

    ProductDTO updateProduct(ProductDTO product,int id);

    void deleteProduct(int id);

}
