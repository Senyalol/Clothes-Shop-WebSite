package com.ClotheShop.CShop.Controller;

import com.ClotheShop.CShop.DTO.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ClotheShop.CShop.Facade.Review.ReviewFacade;

import java.util.List;

//Мои отзывы

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5174"})
@RestController
@RequestMapping("api/reviews")
public class ReviewController {

    private final ReviewFacade facade;

    @Autowired
    public ReviewController(ReviewFacade facade) {
        this.facade = facade;
    }

    //Админский функционал

    //Редактировать отзыв
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/update/{id}")
    public ReviewDTO updateReview(@PathVariable int id, @RequestBody ReviewDTO reviewDTO) {
        return facade.updateReview(id, reviewDTO);
    }

    //Удалить отзыв
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteReview(@PathVariable int id) {
        facade.deleteReviewById(id);
    }

    //Пользовательский функционал

    //Оставить отзыв
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @PostMapping
    public ReviewDTO addReview(@RequestHeader("Authorization") String token ,@RequestBody ReviewDTO reviewDTO) {
        return facade.addReview(token,reviewDTO);
    }

    //Получить конкретный отзыв по id
    @GetMapping("/getCertRev/{id}")
    public ReviewDTO getCertReview(@PathVariable int id) {
        return facade.getReviewById(id);
    }

    //Получить все отзывы
    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return facade.getAllReviews();
    }

    //Редактировать свой отзыв
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @PatchMapping("/changeYours/{id}")
    public ReviewDTO changeReview(@PathVariable int id, @RequestBody ReviewDTO reviewDTO, @RequestHeader("Authorization") String token) {
        return facade.changeYouReview(id, reviewDTO, token);
    }

    //Удалить свой отзыв
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @DeleteMapping("/deleteYours/{id}")
    public void deleteYourReview(@PathVariable int id, @RequestHeader("Authorization") String token, @RequestBody ReviewDTO reviewDTO) {
        facade.deleteYouReview(id, reviewDTO, token);
    }

}