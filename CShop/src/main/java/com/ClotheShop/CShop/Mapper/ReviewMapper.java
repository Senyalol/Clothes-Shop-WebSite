package com.ClotheShop.CShop.Mapper;

import com.ClotheShop.CShop.DTO.ReviewDTO;
import com.ClotheShop.CShop.Entity.Review;
import com.ClotheShop.CShop.Repository.ProductRepository;
import com.ClotheShop.CShop.Repository.UserRepository;
import com.ClotheShop.CShop.Security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    //Возможно зря
    private final JWTService jwtService;

    @Autowired
    public ReviewMapper(UserRepository userRepository, ProductRepository productRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.jwtService = jwtService;
    }

    //Из DTO в сущность
    public Review toEntity(ReviewDTO dto) {

        Review review = new Review();
        review.setProduct(productRepository.findById(dto.getProduct()).get());
        review.setUser(userRepository.findById(dto.getUser()).get());
        review.setReview(dto.getReview());

        return review;
    }

    //Для создания
    public Review toEntityWithUser(String token ,ReviewDTO dto) {

            String payLoadData = token;

            if(payLoadData != null && payLoadData.startsWith("Bearer ")) {
                payLoadData = payLoadData.substring(7).trim();
            }



        Review review = new Review();
        review.setProduct(productRepository.findById(dto.getProduct()).get());
        review.setUser(userRepository.findByLogin(jwtService.getLoginFromToken(payLoadData)).get());
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