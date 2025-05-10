package za.ac.cput.domain;

import java.time.LocalDate;

public class   Review {
    private int reviewID;
    private int productID;
    private int userID;
    private int rating;
    private String comment;
    private LocalDate reviewDate;
    private boolean isApproved;

    public Review() {
    }

    public Review(Builder builder) {
        this.reviewID = builder.reviewID;
        this.productID = builder.productID;
        this.userID = builder.userID;
        this.rating = builder.rating;
        this.comment = builder.comment;
        this.reviewDate = builder.reviewDate;
        this.isApproved = builder.isApproved;
    }

    public int getReviewID() {
        return reviewID;
    }

    public int getProductID() {
        return productID;
    }

    public int getUserID() {
        return userID;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public boolean isApproved() {
        return isApproved;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewID=" + reviewID +
                ", productID=" + productID +
                ", userID=" + userID +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", reviewDate=" + reviewDate +
                ", isApproved=" + isApproved +
                '}';
    }
    public static class Builder {
        private int reviewID;
        private int productID;
        private int userID;
        private int rating;
        private String comment;
        private LocalDate reviewDate;
        private boolean isApproved;


        public Builder setReviewID(int reviewID) {
            this.reviewID = reviewID;
            return this;
        }

        public Builder setProductID(int productID) {
            this.productID = productID;
            return this;
        }

        public Builder setUserID(int userID) {
            this.userID = userID;
            return this;
        }

        public Builder setRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder setReviewDate(LocalDate reviewDate) {
            this.reviewDate = reviewDate;
            return this;
        }

        public Builder setApproved(boolean approved) {
            isApproved = approved;
            return this;
        }
        public Builder copy(Review review) {
            this.reviewID = review.getReviewID();
            this.productID = review.getProductID();
            this.userID = review.getUserID();
            this.rating = review.getRating();
            this.comment = review.getComment();
            this.reviewDate = review.getReviewDate();
            this.isApproved = review.isApproved();
            return this;
        }
        public Review build() {
            return new Review(this);
        }

    }

}
