package org.kainos.ea.api;

import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.ProductValidator;
import org.kainos.ea.db.ProductDao;

import java.sql.SQLException;
import java.util.*;

public class ProductService {

    private ProductDao productDao = new ProductDao();
    private ProductValidator productValidator = new ProductValidator();

    public List<Product> getAllProducts() throws FailedToGetProductsException {

        List<Product> productList = null;
        try {
            productList = productDao.getAllProducts();
        } catch (SQLException e) {
            throw new FailedToGetProductsException();
        }

        double totalPrice = 0;

        /* Using a for loop to calculate the total price
        for(int i = 0; i < productList.size(); i++){
            totalPrice += productList.get(i).getPrice();
        }
        */

        /* Using a for each loop to calculate the total price
        for(Product myProduct : productList){
            totalPrice += myProduct.getPrice();
        }
         */

        /* Using a while loop to calculate the total price
        Iterator<Product> productIterator = productList.iterator();

        while(productIterator.hasNext()){
            Product product = productIterator.next();
            totalPrice += product.getPrice();
        }
        */

        /* Using a do while loop to calculate the total price
        Iterator<Product> productIterator = productList.iterator();
        do{
            Product product = productIterator.next();
            totalPrice += product.getPrice();
        }while(productIterator.hasNext());
        */

        /* Using streams to calculate total price
        totalPrice = productList.stream().mapToDouble(product -> product.getPrice()).sum();
        */

        /* Using a for each loop to calculate the total price for prices less than £100
        for(Product myProduct : productList){
            if(myProduct.getPrice() < 100) {
                totalPrice += myProduct.getPrice();
            }
        }
         */

        /* Using a for each loop to calculate the total price for prices more than or eqaul to £100
        for(Product myProduct : productList){
            if(myProduct.getPrice() >= 100) {
                totalPrice += myProduct.getPrice();
            }
        }
         */

        /* Update your `ProductService` class to use a for each loop to print out
        the price of some of the products in your database with a unique message and
        default the rest to a generic message

         for(Product product: productList){
            switch(product.getName()){
                case("Coffee machine"):
                    System.out.println("This is the coffee machine price: £" + product.getPrice());
                    break;
                case("Coffee mugs"):
                    System.out.println("This is the coffee mugs price: £" + product.getPrice());
                    break;
                case("Water bottle"):
                    System.out.println("This is the water bottle price: £" + product.getPrice());
                    break;
                default:
                    System.out.println("This is the price of the other office equipment: £" + product.getPrice());
            }
        }
         */
        //System.out.println("Total Price of all the products: £" + totalPrice);

        /* Create a list of integers with at least one duplicated value and print out the list
        List<Integer> intList = Arrays.asList(1, 2, 2, 4, 5);

        intList.stream().forEach(System.out::println);
         */

        /*Create a set of integers and assign it the values from the list created above and print out the set.
        Notice the difference between the values in the list and the values in the set.
        A set only can be used to store unique values.

        List<Integer> intList = Arrays.asList(1, 2, 2, 4, 5);
        Set<Integer> intSet = new HashSet<>(intList);

        intSet.stream().forEach(System.out::println);
         */

        Collections.sort(productList);

        //productList.stream().forEach(System.out::println);

        // Printing out the product with the lowest price
        //System.out.println(Collections.min(productList));

        //Print out the product with the max price
        //System.out.println(Collections.max(productList));

        //Printing out the products with a price more than £10
        //productList.stream().filter(product -> product.getPrice() > 10).forEach(System.out::println);

        //Printing out the products with a price less than £10
        //roductList.stream().filter(product -> product.getPrice() <= 10).forEach(System.out::println);

        /*try {
            Product product = productList.get(10000);
        }catch(IndexOutOfBoundsException e){
            System.err.println(e.getMessage());
        }*/

        System.out.println(productList);

        return productList;
    }

    public Product getProductById(int id) throws FailedToGetProductsException, ProductDoesNotExistException {

        try{
            Product product = productDao.getProductById(id);

            if(product == null){
                throw new ProductDoesNotExistException();
            }

            return product;
        }catch(SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToGetProductsException();
        }
    }

    public int createProduct(ProductRequest product) throws FailedToCreateProductException, InvalidProductException{
        try{
            String validation = productValidator.isValidProduct(product);

            if(validation != null){
                throw new InvalidProductException(validation);
            }

            int id = productDao.createProduct(product);

            if(id == -1){
                throw new FailedToCreateProductException();
            }
            return id;
        } catch(SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToCreateProductException();
        }
    }

    public void updateProduct(int id, ProductRequest product) throws InvalidProductException, ProductDoesNotExistException, FailedToUpdateProductException {
        try{
            String validation = productValidator.isValidProduct(product);

            if(validation != null){
                throw new InvalidProductException(validation);
            }

            Product productToUpdate = productDao.getProductById(id);

            if(productToUpdate == null){
                throw new ProductDoesNotExistException();
            }

            productDao.updateProduct(id, product);
        } catch (SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToUpdateProductException();
        }
    }

    public void deleteProduct(int id) throws ProductDoesNotExistException, FailedToDeleteProductException {
        try {
            Product productToDelete = productDao.getProductById(id);

            if(productToDelete == null){
                throw new ProductDoesNotExistException();
            }

            productDao.deleteProduct(id);
        } catch (SQLException e) {
            throw new FailedToDeleteProductException();
        }
    }

}
