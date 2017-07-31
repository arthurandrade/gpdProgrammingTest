package com.gpd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpd.model.checkout.Invoice;
import com.gpd.model.checkout.Order;
import com.gpd.model.customer.Customer;
import com.gpd.model.customer.Customers;
import com.gpd.model.product.Product;
import com.gpd.service.CheckoutService;
import com.gpd.service.CustomerService;
import com.gpd.service.ProductService;

@RestController
public class CheckoutController {

    @Autowired
    CheckoutService checkoutService;

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> listAllProducts() {
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> listAllUsers() {
        List<Customer> customers = customerService.findAllCustomers();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> addProduct(@RequestBody Order order) {
        Customers customer = customerService.findCustomerBy(order.getCustomer());
        checkoutService.add(customer.getRules());
        checkoutService.addProductFor(order.getProduct());
        Invoice invoice = checkoutService.getInvoice();
        return new ResponseEntity<Invoice>(invoice, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public ResponseEntity<?> checkout() {
        Invoice invoice = checkoutService.getInvoice();
        checkoutService.clear();
        return new ResponseEntity<Invoice>(invoice, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/clean", method = RequestMethod.DELETE)
    public ResponseEntity<?> clean() {
        checkoutService.clear();
        Invoice invoice = checkoutService.getInvoice();
        return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
    }
}
