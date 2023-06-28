package org.kainos.ea.client;

public class FailedToCreateOrderException extends Exception {
    @Override
    public String getMessage(){
        return "Failed to create order";
    }
}
