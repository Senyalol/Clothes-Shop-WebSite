package com.ClotheShop.CShop.Facade.ProductImage;

import com.ClotheShop.CShop.DTO.ProductImageDTO;
import com.ClotheShop.CShop.Mapper.ProductImageMapper;
import com.ClotheShop.CShop.Service.ProductImage.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductImageFacadeImpl implements ProductImageFacade {

    private final ProductImageMapper productImageMapper;
    private final ProductImageService productImageService;

    @Autowired
    public ProductImageFacadeImpl(ProductImageMapper productImageMapper, ProductImageService productImageService) {
        this.productImageMapper = productImageMapper;
        this.productImageService = productImageService;
    }

    @Override
    public ProductImageDTO addProductImage(ProductImageDTO dto) {
        return productImageMapper.toDTO(productImageService.addProductImage(productImageMapper.toEntity(dto)));
    }

    @Override
    public List<ProductImageDTO> getAllProductImages() {
        return productImageService.getAllProductImages()
                .stream()
                .map(x -> productImageMapper.toDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public ProductImageDTO getProductImageById(int id) {
        return productImageMapper.toDTO(productImageService.getProductImageById(id));
    }

    @Override
    public ProductImageDTO updateProductImage(int id, ProductImageDTO dto) {
        return productImageMapper.toDTO(productImageService.updateProductImage(id,productImageMapper.toEntity(dto)));
    }

    @Override
    public void deleteById(int id) {
        productImageService.deleteById(id);
    }

}