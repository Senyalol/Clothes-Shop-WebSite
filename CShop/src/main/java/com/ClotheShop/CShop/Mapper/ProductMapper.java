package com.ClotheShop.CShop.Mapper;

import com.ClotheShop.CShop.DTO.ProductDTO.ProductDTO;
import com.ClotheShop.CShop.Entity.Product;
import com.ClotheShop.CShop.Service.Product.FilterFiles.FilterProducts;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    //Из DTO в сущность
    public Product toEntity(ProductDTO productDTO) {

        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setAmount(productDTO.getAmount());
        product.setAvailability(productDTO.getAvailability());
        product.setColor(productDTO.getColor());
        product.setSize(productDTO.getSize());
        product.setSex(productDTO.getSex());
        product.setCategory(productDTO.getCategory());
        product.setType(productDTO.getType());

        return product;
    }

    //Из сущности в DTO
    public ProductDTO toDTO(Product product) {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setAmount(product.getAmount());
        productDTO.setAvailability(product.getAvailability());
        productDTO.setColor(product.getColor());
        productDTO.setSize(product.getSize());
        productDTO.setSex(product.getSex());
        productDTO.setCategory(product.getCategory());
        productDTO.setType(product.getType());

        return productDTO;

    }

    //Маппинг для фильтров
    public FilterProducts filterMapping(ProductDTO filterDTO) {

        FilterProducts filterP = new FilterProducts();
        filterP.setName(filterDTO.getName());
        filterP.setColor(filterDTO.getColor());
        filterP.setSize(filterDTO.getSize());
        filterP.setSex(filterDTO.getSex());
        filterP.setCategory(filterDTO.getCategory());
        filterP.setType(filterDTO.getType());

        return filterP;
    }

}
