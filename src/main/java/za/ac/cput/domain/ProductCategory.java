package za.ac.cput.domain;

public class ProductCategory {
    private String categoryId;
    private String categoryName;

    // Private constructor
    private ProductCategory(Builder builder) {
        this.categoryId = builder.categoryId;
        this.categoryName = builder.categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    // Builder class
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
