package com.ClotheShop.CShop.Mapper;

import com.ClotheShop.CShop.DTO.ReviewDTO;
import com.ClotheShop.CShop.Entity.Review;
import com.ClotheShop.CShop.Repository.ProductRepository;
import com.ClotheShop.CShop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ReviewMapper(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    //Из DTO в сущность
    public Review toEntity(ReviewDTO dto) {

        Review review = new Review();
        review.setProduct(productRepository.findById(dto.getProduct()).get());
        review.setUser(userRepository.findById(dto.getUser()).get());
        review.setReview(dto.getReview());

        return review;
    }

    //Из сущности в DTO
    public ReviewDTO toDTO(Review review) {

        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setProduct(review.getProduct().getId());
        dto.setUser(review.getUser().getId());
        dto.setReview(review.getReview());

        return dto;
    }

}