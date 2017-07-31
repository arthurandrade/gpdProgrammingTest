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
import com.gpd.model.customer.Ford;
import com.gpd.rules.ProductDiscount;
import com.gpd.service.CheckoutService;
import com.gpd.service.CheckoutServiceImpl;
import com.gpd.service.ProductService;
import com.gpd.service.ProductServiceImpl;

@RunWith(SpringRunner.class)
public class FordCustomerTest {

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
    public void ShouldCalculateDiscountFord() {

        Customers ford = new Ford();
        service.add(ford.getRules());
        service.addProductFor("premium");
        service.addProductFor("premium");
        service.addProductFor("premium");
        service.addProductFor("standout");
        service.addProductFor("standout");
        service.addProductFor("standout");
        service.addProductFor("classic");
        service.addProductFor("classic");
        service.addProductFor("classic");

        assertEquals(2909.91, service.total(), 0.001);
    }

    @Test
    public void ShouldCalculateNewSetRulesFord() {

        Customers ford = new Ford();
        ford.setRules(new ProductDiscount("standout", 299.99));
        service.add(ford.getRules());
        service.addProductFor("standout");
        service.addProductFor("standout");
        service.addProductFor("standout");
        service.addProductFor("premium");
        assertEquals(1294.96, service.total(), 0.001);
    }

}
