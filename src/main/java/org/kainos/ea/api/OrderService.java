package org.kainos.ea.api;

import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.OrderValidator;
import org.kainos.ea.db.OrderDao;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {
    private OrderDao orderDao = new OrderDao();
    private OrderValidator orderValidator = new OrderValidator();
    public List<Order> getAllOrders() throws FailedToGetOrdersException {

        List<Order> orderList = null;
        try {
            orderList = orderDao.getAllOrders();
        } catch (SQLException e) {
            throw new FailedToGetOrdersException();
        }

        //Collections.sort(orderList);

        //orderList.stream().forEach(System.out::println);
        //orderList.stream().sorted(Comparator.comparing(Order::getOrderDate)).forEach(System.out::println);

        /*
        Date today = new Date();
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, -7);
        Date oldDate = cal.getTime();
        orderList.stream()
                .filter(order -> order.getOrderDate()
                .after(oldDate))
                .sorted(Comparator.comparing(Order::getOrderDate))
                .forEach(System.out::println);
        */

        //orderList.stream().filter(order -> order.getOrderDate().after(new Date(2023, 6, 21, 0, 0, 0))).forEach(System.out::println);
        //orderList.stream().filter(order -> order.getCustomerId() == 1).forEach(System.out::println);

        //System.out.println(Collections.max(orderList));
        //System.out.println(Collections.min(orderList));

        //System.out.println("Number of orders: " + orderList.size());

        /* Update your `OrderService` to show the customer ID with the most orders
        int maxCount = 0;
        int count = 0;
        int id = 0;

        for(int i = 0; i < orderList.size(); i++){
            for(int j = 0; j < orderList.size(); j++){
                if(orderList.get(i).getCustomerId() == orderList.get(j).getCustomerId()){
                    count++;
                }
            }
            if(count >= maxCount){
                maxCount = count;
                id = orderList.get(i).getCustomerId();
            }
            count = 0;
        }

        System.out.println("Customer with most orders: " + id);

        Integer mostOrders = orderList.stream()
                .collect(Collectors.groupingBy(Order::getCustomerId, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
         */

        //Map<Integer, Long> countOrderMap = orderList.stream()
        //       .collect(Collectors)

        /* Update your `OrderService` to show the customer ID with the least orders
        int minCount = orderList.size();
        int count = 0;
        int id = 0;

        for(int i = 0; i < orderList.size(); i++){
            for(int j = 0; j < orderList.size(); j++){
                if(orderList.get(i).getCustomerId() == orderList.get(j).getCustomerId()){
                    count++;
                }
            }
            if(count < minCount){
                minCount = count;
                id = orderList.get(i).getCustomerId();
            }
            count = 0;
        }

        System.out.println("Customer with least orders: " + id);

        Integer leastOrders = orderList.stream()
                .collect(Collectors.groupingBy(Order::getCustomerId, Collectors.counting()))
                .entrySet().stream().min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
         */

        return orderList;
    }

    public Order getOrderById(int id) throws FailedToGetOrdersException, OrderDoesNotExistException {

        try{
            Order order = orderDao.getOrderById(id);

            if(order == null){
                throw new OrderDoesNotExistException();
            }

            return order;
        }catch(SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToGetOrdersException();
        }
    }

    public int createOrder(OrderRequest order) throws FailedToCreateOrderException, InvalidOrderException{
        try{
            String validation = orderValidator.isValidOrder(order);

            if(validation != null){
                throw new InvalidOrderException(validation);
            }

            int id = orderDao.createOrder(order);

            if(id == -1){
                throw new FailedToCreateOrderException();
            }
            return id;
        } catch(SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToCreateOrderException();
        }
    }

    public void updateOrder(int id, OrderRequest order) throws InvalidOrderException, OrderDoesNotExistException, FailedToUpdateOrderException {
        try{
            String validation = orderValidator.isValidOrder(order);

            if(validation != null){
                throw new InvalidOrderException(validation);
            }

            Order orderToUpdate = orderDao.getOrderById(id);

            if(orderToUpdate == null){
                throw new OrderDoesNotExistException();
            }

            orderDao.updateOrder(id, order);
        } catch (SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToUpdateOrderException();
        }
    }

    public void deleteOrder(int id) throws OrderDoesNotExistException, FailedToDeleteOrderException {
        try {
            Order orderToDelete = orderDao.getOrderById(id);

            if(orderToDelete == null){
                throw new OrderDoesNotExistException();
            }

            orderDao.deleteOrder(id);
        } catch (SQLException e) {
            throw new FailedToDeleteOrderException();
        }
    }
}
