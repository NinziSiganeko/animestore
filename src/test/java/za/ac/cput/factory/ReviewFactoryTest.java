package za.ac.cput.factory;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Review;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReviewFactoryTest {
    public static Review review1 = ReviewFactory.createReview(1, 101, 201, 5, "Excellent product", LocalDate.now(), true);
    public static Review review2 = ReviewFactory.createReview(2, 102, 202, 3, "Average quality", LocalDate.now(), false);
    public static Review review3 = ReviewFactory.createReview(0, 0, 0, 0, "", null, false); // Invalid test

    @Test
    @Order(1)
    public void testCreateReview() {
        assertNotNull(review1);
        System.out.println(review1.toString());
    }

    @Test
    @Order(2)
    public void testCreateReviewWithAllAttributes() {
        assertNotNull(review2);
        System.out.println(review2.toString());
    }

    @Test
    @Order(3)
    public void testCreateReviewThatFails() {
        assertNull(review3);
        System.out.println(review3);
    }

    @Test
    @Disabled
    @Order(4)
    public void testNotImplemented() {
    }
}
