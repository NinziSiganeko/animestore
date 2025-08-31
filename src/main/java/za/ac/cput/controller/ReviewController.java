package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.domain.Review;
import za.ac.cput.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create")
    public Review create(@RequestBody Review review) {
        return reviewService.create(review);
    }

    @GetMapping("/getAll")
    public List<Review> getAll() {
        return reviewService.getAll();
    }
    @PostMapping("/update")
    public Review update(@RequestBody Review review) {
        return reviewService.update(review);
    }


    @GetMapping("/read/{reviewId}")
    public Review getById(@PathVariable Long reviewId) {
        return reviewService.read(reviewId);
    }

    @DeleteMapping("/delete/{reviewId}")
    public void delete(@PathVariable Long reviewId) {
        reviewService.delete(reviewId);
    }
}
