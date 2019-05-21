package org.aut.digikala.model;

public class Carts {
    private int cartId;
    private String orderDate, deliverDate;
    private boolean isDelivered;
    private double price;

    public Carts(int cartId, String orderDate, String deliverDate, boolean isDelivered, double price) {
        this.cartId = cartId;
        this.orderDate = orderDate;
        this.deliverDate = deliverDate;
        this.isDelivered = isDelivered;
        this.price = price;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(String deliverDate) {
        this.deliverDate = deliverDate;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
