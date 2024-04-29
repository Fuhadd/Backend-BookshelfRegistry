package com.shelfscribe.backendbookshelfregistry.exceptions;

public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
}
