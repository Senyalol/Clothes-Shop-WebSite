package com.ClotheShop.CShop.Facade.ProductImage;

import com.ClotheShop.CShop.DTO.ProductImageDTO;
import java.util.List;

public interface ProductImageFacade {

    List<ProductImageDTO> getAllProductImages();

    ProductImageDTO getProductImageById(int id);

    ProductImageDTO addProductImage(ProductImageDTO dto);

    ProductImageDTO updateProductImage(int id,ProductImageDTO dto);

    void deleteById(int id);

}