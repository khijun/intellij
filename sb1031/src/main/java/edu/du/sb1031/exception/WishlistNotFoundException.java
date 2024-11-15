package edu.du.sb1031.exception;

public class WishlistNotFoundException extends RuntimeException {
    public WishlistNotFoundException() {
        super("Wishlist not found");
    }
}
