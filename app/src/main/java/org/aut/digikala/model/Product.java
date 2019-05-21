package org.aut.digikala.model;

import org.aut.digikala.R;

public class Product {
    private int productId;
    private String productName;
    private double price, discount;
    private int stock;
    private String shortdesc;
    private double rating;
    private int image;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", stock=" + stock +
                ", shortdesc='" + shortdesc + '\'' +
                ", rating=" + rating +
                ", image=" + image +
                '}';
    }

    public Product(int productId, String productName, double price, double discount, int stock, String shortdesc, double rating, int image) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.image = image;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
