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
import com.gpd.model.customer.Unilever;
import com.gpd.service.CheckoutService;
import com.gpd.service.CheckoutServiceImpl;
import com.gpd.service.ProductService;
import com.gpd.service.ProductServiceImpl;

@RunWith(SpringRunner.class)
public class UnileverCustomerTest {

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
    }

    @Autowired
    private CheckoutService service;

    @After
    public void cleanCart() {
        service.clear();
    }

    @Test
    public void ShouldCalculateDiscount() {

        Customers unilever = new Unilever();
        service.add(unilever.getRules());
        service.addProductFor("classic");
        service.addProductFor("classic");
        service.addProductFor("classic");
        service.addProductFor("premium");

        assertEquals(934.97, service.total(), 0.01);
    }

    @Test
    public void ShouldCalculateDiscount2() {
        Customers unilever = new Unilever();
        service.add(unilever.getRules());
        service.addProductFor("classic");
        service.addProductFor("classic");
        service.addProductFor("classic");
        service.addProductFor("premium");
        service.addProductFor("classic");

        assertEquals(1204.96, service.total(), 0.01);
    }

}
