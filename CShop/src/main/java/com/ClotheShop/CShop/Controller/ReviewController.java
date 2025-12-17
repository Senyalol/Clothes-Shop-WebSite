package com.ClotheShop.CShop.Controller;

import com.ClotheShop.CShop.DTO.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    //Получить все отзывы
    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return facade.getAllReviews();
    }

    //Оставить отзыв
    @PostMapping
    public ReviewDTO addReview(@RequestBody ReviewDTO reviewDTO) {
        return facade.addReview(reviewDTO);
    }

    //Редактировать отзыв
    @PatchMapping("/update/{id}")
    public ReviewDTO updateReview(@PathVariable int id, @RequestBody ReviewDTO reviewDTO) {
        return facade.updateReview(id, reviewDTO);
    }

    //Удалить отзыв
    @DeleteMapping("/delete/{id}")
    public void deleteReview(@PathVariable int id) {
        facade.deleteReviewById(id);
    }

    //Получить конкретный отзыв по id
    @GetMapping("/getCertRev/{id}")
    public ReviewDTO getCertReview(@PathVariable int id) {
        return facade.getReviewById(id);
    }

}