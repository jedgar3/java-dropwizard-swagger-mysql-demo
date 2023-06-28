package org.kainos.ea.client;

public class FailedToGetCustomerIDException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to get customer ids";
    }
}
