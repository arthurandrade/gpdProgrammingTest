package com.gpd.service;

import java.util.List;

import com.gpd.model.customer.Customer;
import com.gpd.model.customer.Customers;

public interface CustomerService {

    Customers findCustomerBy(String id);

    List<Customer> findAllCustomers();

}
