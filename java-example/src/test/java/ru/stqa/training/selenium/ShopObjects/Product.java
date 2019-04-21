package ru.stqa.training.selenium.ShopObjects;

import java.util.Objects;

public class Product {

    String productName;
    String salesPrice;
    String regularPrice;

    public String getProductName() {
        return productName;
    }

    public Product setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getSalesPrice() {
        return salesPrice;
    }

    public Product setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
        return this;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public Product setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productName.equals(product.productName) &&
                salesPrice.equals(product.salesPrice) &&
                regularPrice.equals(product.regularPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, salesPrice, regularPrice);
    }

}
