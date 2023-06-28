package org.kainos.ea.client;

public class ProductDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "A product with that id does not exist";
    }
}
