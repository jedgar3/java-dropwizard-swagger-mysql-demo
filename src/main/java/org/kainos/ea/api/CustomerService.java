package org.kainos.ea.api;

import org.kainos.ea.cli.Order;
import org.kainos.ea.client.FailedToGetCustomerIDException;
import org.kainos.ea.client.FailedToGetOrdersException;
import org.kainos.ea.db.CustomerDao;
import org.kainos.ea.db.OrderDao;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    private CustomerDao customerDao = new CustomerDao();
    public List<Integer> getAllCustomerID() throws FailedToGetCustomerIDException {

        List<Integer> customerIDs = null;
        try {
            customerIDs = customerDao.getAllCustomerID();
            return customerIDs;
        } catch (SQLException e) {
            throw new FailedToGetCustomerIDException();
        }
    }
}
