package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
public class ProductCategory {

    @Id
    private String categoryId;
    private String categoryName;

    protected ProductCategory() {}

    public ProductCategory(Builder builder) {
        this.categoryId = builder.categoryId;
        this.categoryName = builder.categoryName;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public String getCategoryId() { return categoryId; }
    public String getCategoryName() { return categoryName; }

    public static class Builder {
        private String categoryId;
        private String categoryName;

        public Builder setCategoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder setCategoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public ProductCategory build() {
            return new ProductCategory(this);
        }
    }
}
