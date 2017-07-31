package com.gpd.customers;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.gpd.model.customer.Customers;
import com.gpd.rules.ProductDiscount;
import com.gpd.service.CheckoutService;
import com.gpd.service.CheckoutServiceImpl;
import com.gpd.service.CustomerService;
import com.gpd.service.CustomerServiceImpl;
import com.gpd.service.ProductService;
import com.gpd.service.ProductServiceImpl;

@RunWith(SpringRunner.class)
public class AppleCustomerTest {

    @TestConfiguration
    static class Configuration {
        @Bean
        public ProductService serviceA() {
            return new ProductServiceImpl();
        }

        @Bean
        public CheckoutService service() {
            return new CheckoutServiceImpl();
        }

        @Bean
        public CustomerService serviceB() {
            return new CustomerServiceImpl();
        }
    }

    @Autowired
    private CheckoutService service;

    @Autowired
    private CustomerService customerService;

    @After
    public void cleanCart() {
        service.clear();
    }

    @Test
    public void ShouldCalculateDiscountApple()
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        Customers customer = customerService.findCustomerBy("apple");
        service.add(customer.getRules());
        service.addProductFor("standout");
        service.addProductFor("standout");
        service.addProductFor("standout");
        service.addProductFor("premium");
        assertEquals(1294.96, service.total(), 0.001);
    }

    @Test(expected = RuntimeException.class)
    public void ShouldThrowException() {
        service.add(new ProductDiscount("standout", 299.99));
        service.addProductFor("stanrrdout");
    }
}
