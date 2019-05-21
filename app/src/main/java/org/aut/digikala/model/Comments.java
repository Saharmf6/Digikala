package org.aut.digikala.model;

public class Comments {
    private int commentId, productId;
    private String commentText;
    private String userName;

    public Comments(int commentId, int productId, String commentText, String userName) {
        this.commentId = commentId;
        this.productId = productId;
        this.commentText = commentText;
        this.userName = userName;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
