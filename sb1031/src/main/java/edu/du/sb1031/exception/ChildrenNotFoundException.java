package edu.du.sb1031.exception;

public class ChildrenNotFoundException extends RuntimeException {
    public ChildrenNotFoundException() {
        super("Children not found");
    }
}
