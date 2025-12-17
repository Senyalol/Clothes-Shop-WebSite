package com.ClotheShop.CShop.Service.Product.Checks.Filters;

import com.ClotheShop.CShop.Entity.Product;
import com.ClotheShop.CShop.Repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MainFilter {

    private List<Filter> filters;

    public MainFilter(List<Filter> filters) {
        this.filters = filters;
    }

    public List<Product> getFilters(Product product, ProductRepository productRepository) {

        List<Product> result = productRepository.findAll();

        for(Filter filter : filters) {

            result.stream()
                    .filter(f -> filter.getFilter(product) != null && filter.getFilter(product).equals(f))
                    .collect(Collectors.toList());

        }

        return result;
    }


}