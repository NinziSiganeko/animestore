/* ProductCategory.java
   ProductCategory POJO class based on UML diagram
   Author: onthatile 221230793
   Date: 11 May 2025
*/

package za.ac.cput.domain;

public class ProductCategory {
    private int categoryId;
    private String categoryName;

    private ProductCategory() {}

    public ProductCategory(Builder builder) {
        this.categoryId = builder.categoryId;
        this.categoryName = builder.categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public static class Builder {
        private int categoryId;
        private String categoryName;

        public Builder setCategoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder setCategoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public Builder copy(ProductCategory category) {
            this.categoryId = category.getCategoryId();
            this.categoryName = category.getCategoryName();
            return this;
        }

        public ProductCategory build() {
            return new ProductCategory(this);
        }
    }
}
