package com.ClotheShop.CShop.Mapper;

import com.ClotheShop.CShop.DTO.ProductImageDTO;
import com.ClotheShop.CShop.Entity.ProductImage;
import com.ClotheShop.CShop.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductImageMapper {

    private final ProductRepository productRepository;

    @Autowired
    public ProductImageMapper(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Из DTO в сущность
    public ProductImage toEntity(ProductImageDTO dto){

        ProductImage entity = new ProductImage();
        entity.setProduct(productRepository.findById(dto.getProduct()).get());
        entity.setImage(dto.getImage());

        return entity;
    }

    //Из сущности в DTO
    public ProductImageDTO toDTO(ProductImage entity){

        ProductImageDTO dto = new ProductImageDTO();
        dto.setProductImageId(entity.getId());
        dto.setProduct(entity.getProduct().getId());
        dto.setImage(entity.getImage());

        return dto;
    }

}
