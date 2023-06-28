package org.kainos.ea.core;

import org.kainos.ea.cli.ProductRequest;

public class ProductValidator {
    public String isValidProduct(ProductRequest product){
        if(product.getName().length() > 100){
            return "Name is greater than 50 characters";
        }

        if(product.getDescription().length() > 500){
            return "Description is more than 500 characters";
        }

        if(product.getPrice() < 7.5){
            return "Price is less than £7.50";
        }

        return null;

    }
}
