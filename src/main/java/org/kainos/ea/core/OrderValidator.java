package org.kainos.ea.core;

import org.kainos.ea.api.CustomerService;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.FailedToGetCustomerIDException;
import org.kainos.ea.db.CustomerDao;

import java.sql.SQLException;
import java.util.*;

public class OrderValidator {
    public String isValidOrder(OrderRequest order){
        CustomerService customerService = new CustomerService();
        try {
            List<Integer> customerIds = customerService.getAllCustomerID();

            if (!customerIds.contains(order.getCustomerId())) {
                return "CustomerID not found";
            }
        }catch (FailedToGetCustomerIDException e) {
            return e.getMessage();
        }

        Date today = new Date();
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.YEAR, -1);
        Date oldDate = cal.getTime();

        if(!order.getOrderDate().after(oldDate)){
            return "Order placed over a year ago";
        }

        return null;

    }
}
