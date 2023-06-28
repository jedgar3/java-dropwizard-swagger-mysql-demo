package org.kainos.ea.cli;


public class Product implements Comparable<Product>{

    private int productId;
    private String name;
    private String description;
    private double price;

    public Product(int productId, String name, String description, double price) {
        setProductId(productId);
        setName(name);
        setDescription(description);
        setPrice(price);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(Product product) {
        //return this.getName().compareTo(product.getName());
        return Double.compare(this.getPrice(), product.getPrice());
    }

    @Override
    public String toString(){
        return "Product name: " + this.getName() + ", Product price: £" + this.getPrice();
    }
}
