package com.ClotheShop.CShop.Service.Product;

import com.ClotheShop.CShop.Entity.Product;
import com.ClotheShop.CShop.Repository.ProductRepository;
import com.ClotheShop.CShop.Service.Product.Checks.CreateChecks.*;
import com.ClotheShop.CShop.Service.Product.Checks.UpdateChecks.*;
import com.ClotheShop.CShop.Service.Product.FilterFiles.FilterProducts;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@JsonSerialize
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private static Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product addProduct(Product product) {

        List<ProductCreateCheck> createChecks = new ArrayList<>(Arrays.asList(
                new ProductNameCreateCheck(),
                new ProductPriceCreateCheck(),
                new ProductAmountCreateCheck(),
                new ProductAvailabilityCreateCheck(),
                new ProductColorCreateCheck(),
                new ProductSizeCreateCheck(),
                new ProductSexCreateCheck(),
                new ProductCategoryCreateCheck(),
                new ProductTypeCreateCheck()
        ));

        MainProductCreateCheck checks = new MainProductCreateCheck(createChecks);

        if(checks.AllChecks(product)){

        productRepository.save(product);
        LOGGER.info("Product: {} - successfully saved", product.getName());
            return product;
        }

        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().filter(x -> x.getAvailability())
                .collect(Collectors.toList());
    }

    @Override
    public Product getCertainProduct(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Product updateProduct(Product product, int id) {

        List<ProductUpdateCheck> updateChecks = new ArrayList<>(
                Arrays.asList(
                    new ProductNameUpdateCheck(),
                    new ProductPriceUpdateCheck(),
                    new ProductAmountUpdateCheck(),
                    new ProductAvailabilityUpdateCheck(),
                    new ProductColorUpdateCheck(),
                    new ProductSizeUpdateCheck(),
                    new ProductSexUpdateCheck(),
                    new ProductCategoryUpdateCheck(),
                    new ProductTypeUpdateCheck()
                )
        );

        MainProductUpdateCheck checks = new MainProductUpdateCheck(updateChecks);

        Product oldProduct = productRepository.findById(id).get();
        checks.allChecks(product, oldProduct);
        LOGGER.info("Product data : - was successfully updated");

        return productRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        LOGGER.info("Product: {} - was successfully deleted", productRepository.findById(id).get().getName());
        productRepository.deleteById(id);
    }

    //Фильтр по критериям товара
    @Transactional
    @Override
    public List<Product> filterProduct(FilterProducts filters) {

        return productRepository.findAll()
                .stream()
                .filter(x -> x.getName().equals(filters.getName()) || filters.getName() == null)
                .filter(x -> x.getColor().equals(filters.getColor()) || filters.getColor() == null)
                .filter(x -> x.getSize() == filters.getSize() || filters.getSize() == null)
                .filter(x -> x.getSex().equals(filters.getSex()) || filters.getSex() == null)
                .filter(x -> x.getCategory().equals(filters.getCategory()) || filters.getCategory() == null)
                .filter(x -> x.getType().equals(filters.getType()) || filters.getType() == null)
                .collect(Collectors.toList());

    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

}