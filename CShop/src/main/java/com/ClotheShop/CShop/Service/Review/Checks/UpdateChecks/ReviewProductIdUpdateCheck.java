package com.ClotheShop.CShop.Service.Review.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Review;
import com.ClotheShop.CShop.Repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReviewProductIdUpdateCheck implements ReviewUpdateCheck{

    private ProductRepository productRepository;
    private static Logger LOGGER = LogManager.getLogger(ReviewProductIdUpdateCheck.class);

    public ReviewProductIdUpdateCheck(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void check(Review oldReview, Review newReview) {

        if(newReview != null && newReview.getProduct().getId() != null &&
        productRepository.findById(newReview.getProduct().getId()).isPresent()){

            Integer oldProductId = oldReview.getProduct().getId();
            oldReview.setProduct(newReview.getProduct());
            LOGGER.info("{} Review was updated, old product - {} , new product",oldReview.getId(),oldProductId,newReview.getProduct().getId());

        }

    }

}