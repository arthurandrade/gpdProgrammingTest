package com.gpd.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.gpd.model.customer.Customers;
import com.gpd.model.customer.Customer;
import com.gpd.model.customer.Default;

@Service("customeService")
public class CustomerServiceImpl implements CustomerService {
    private List<Customer> customers;

    public CustomerServiceImpl() {
        this.customers = findAllCustomers();
    }

    @Override
    public Customers findCustomerBy(String id) {
        Optional<Customer> customer = this.customers.stream().filter(prod -> prod.getId().contains(id)).findFirst();
        Customers customerSelected = null;
        Customer custome = customer.orElse(null);
        if (custome != null) {
            try {
                customerSelected = (Customers) Class.forName("com.gpd.model.customer." + custome.getName())
                        .newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                customerSelected = new Default();
            }
        }

        return customerSelected;
    }

    @Override
    public List<Customer> findAllCustomers() {
        final List<Customer> customers = new ArrayList<Customer>();
        try (Stream<String> stream = Files.lines(Paths.get(getClass().getResource("/customers.txt").toURI()))) {
            stream.forEachOrdered(line -> {
                String[] lineprosses = line.split("\\|");
                customers.add(new Customer(lineprosses[0], lineprosses[1]));
            });
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return customers;
    }

}
