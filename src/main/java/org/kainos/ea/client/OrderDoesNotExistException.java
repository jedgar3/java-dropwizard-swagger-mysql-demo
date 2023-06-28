package org.kainos.ea.client;

public class OrderDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "An order with that id does not exist";
    }
}
