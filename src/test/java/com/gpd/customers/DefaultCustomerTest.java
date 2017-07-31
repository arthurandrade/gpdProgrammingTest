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
import com.gpd.model.customer.Default;
import com.gpd.service.CheckoutService;
import com.gpd.service.CheckoutServiceImpl;
import com.gpd.service.ProductService;
import com.gpd.service.ProductServiceImpl;

@RunWith(SpringRunner.class)
public class DefaultCustomerTest {

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

    @After
    public void cleanCart() {
        service.clear();
    }

    @Autowired
    private CheckoutService service;

    @Test
    public void ShouldCalculateDiscount() {

        Customers regular = new Default();
        service.add(regular.getRules());
        service.addProductFor("classic");
        service.addProductFor("standout");
        service.addProductFor("premium");
        assertEquals(987.97, service.total(), 0.001);
    }
}
