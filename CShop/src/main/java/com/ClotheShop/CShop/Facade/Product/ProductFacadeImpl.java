package com.ClotheShop.CShop.Facade.Product;

import com.ClotheShop.CShop.DTO.ProductDTO;
import com.ClotheShop.CShop.Mapper.ProductMapper;
import com.ClotheShop.CShop.Service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductFacadeImpl implements ProductFacade {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @Autowired
    public ProductFacadeImpl(ProductMapper productMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @Override
    public ProductDTO addProduct(ProductDTO product) {
        return productMapper.toDTO(productService.addProduct(productMapper.toEntity(product)));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts().stream()
                .map(x -> productMapper.toDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getCertainProduct(int id) {
        return productMapper.toDTO(productService.getCertainProduct(id));
    }

    @Override
    public ProductDTO updateProduct(ProductDTO product, int id) {
        return productMapper.toDTO(productService.updateProduct(productMapper.toEntity(product),id));
    }

    @Override
    public void deleteProduct(int id) {
        productService.deleteProduct(id);
    }

    @Override
    public List<ProductDTO> filterProduct(ProductDTO product) {
        return productService.filterProduct(productMapper.toEntity(product))
                .stream()
                .map(x -> productMapper.toDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findByName(String name) {
        return productService.findByName(name)
                .stream()
                .map(x -> productMapper.toDTO(x))
                .collect(Collectors.toList());
    }

}
