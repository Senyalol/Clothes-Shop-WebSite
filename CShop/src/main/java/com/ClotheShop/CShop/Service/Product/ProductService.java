package com.ClotheShop.CShop.Service.Product;

import com.ClotheShop.CShop.Entity.Product;
import com.ClotheShop.CShop.Service.Product.FilterFiles.FilterProducts;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    List<Product> getAllProducts();

    Product getCertainProduct(int id);

    Product updateProduct(Product product, int id);

    void deleteProduct(int id);

    List<Product> filterProduct(FilterProducts filters);

    List<Product> findByName(String name);

}