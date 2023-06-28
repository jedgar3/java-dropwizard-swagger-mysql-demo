package org.kainos.ea.client;

public class FailedToCreateProductException extends Exception {
    @Override
    public String getMessage(){
        return "Failed to create product";
    }
}
