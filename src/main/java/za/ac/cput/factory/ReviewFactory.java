package za.ac.cput.factory;
//ReviewFactory class
//Author : S Mzaza (220030898)
//Date: 18 May 2025

import za.ac.cput.domain.Review;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
public class ReviewFactory {

        public static Review createReview(int reviewID, int productID, int userID,
                                          int rating, String comment,
                                          LocalDate reviewDate, boolean isApproved) {

            if (rating < 1 || rating > 5 || Helper.isNullOrEmpty(comment) || reviewDate == null) {
                return null;
            }

            return new Review.Builder()
                    .setRating(rating)
                    .setComment(comment)
                    .setReviewDate(reviewDate)
                    .setApproved(isApproved)
                    .build();
        }
    }




