package org.aut.digikala.model;

public class Orders {
    private String userName, userEmail;
    private int productId, cartId, count;
    private double price;
    private int returned;

    public Orders(String userName,String userEmail, int productId, int cartId, int count, double price, int returned) {
        this.userName = userName;
        this.userEmail = userEmail;

        this.productId = productId;
        this.cartId = cartId;
        this.count = count;
        this.price = price;
        this.returned = returned;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }
}
