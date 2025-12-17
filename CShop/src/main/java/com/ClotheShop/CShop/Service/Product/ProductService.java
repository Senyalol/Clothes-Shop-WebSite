package com.ClotheShop.CShop.Service.Product;

import com.ClotheShop.CShop.Entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    List<Product> getAllProducts();

    Product getCertainProduct(int id);

    Product updateProduct(Product product, int id);

    void deleteProduct(int id);

    List<Product> filterProduct(Product product);

    List<Product> findByName(String name);

}