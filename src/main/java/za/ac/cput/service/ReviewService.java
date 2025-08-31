package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Review;
import za.ac.cput.repository.ReviewRepository;

import java.util.List;

@Service
public class ReviewService implements IReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review read(Long aLong) {
        return reviewRepository.findById(aLong).orElse(null);
    }

    @Override
    public Review update(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public boolean delete(Long aLong) {
        reviewRepository.deleteById(aLong);
        return !reviewRepository.existsById(aLong);
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }
}
