package org.kainos.ea.cli;

import java.util.Date;

public class Order implements Comparable<Order>{

    private int orderId;
    private int customerId;
    private Date orderDate;

    public Order(int orderID, int customerId, Date orderDate) {
        setOrderId(orderID);
        setCustomerId(customerId);
        setOrderDate(orderDate);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderID) {
        this.orderId = orderID;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString(){
        return "Order ID: " + this.getOrderId() + ", Customer ID: " + this.getCustomerId() + ", Order Date: " + this.getOrderDate();
    }

    @Override
    public int compareTo(Order order) {
        //return this.getOrderDate().compareTo(order.getOrderDate());
        return orderDate.compareTo(order.getOrderDate());
    }
}
