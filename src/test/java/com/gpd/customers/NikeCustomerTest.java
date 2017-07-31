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
import com.gpd.model.customer.Nike;
import com.gpd.rules.MoreThanX;
import com.gpd.service.CheckoutService;
import com.gpd.service.CheckoutServiceImpl;
import com.gpd.service.ProductService;
import com.gpd.service.ProductServiceImpl;

@RunWith(SpringRunner.class)
public class NikeCustomerTest {

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
        Customers nike = new Nike();
        service.add(nike.getRules());
        service.add(new MoreThanX("premium", 4, 379.99));
        service.addProductFor("premium");
        service.addProductFor("premium");
        service.addProductFor("premium");
        service.addProductFor("premium");
        double value = service.total();
        assertEquals(1519.96, value, 0.001);
    }

    @Test
    public void ShouldNotApplyDiscount() {
        Customers nike = new Nike();
        service.add(nike.getRules());
        service.addProductFor("premium");
        service.addProductFor("premium");
        service.addProductFor("premium");
        double value = service.total();

        assertEquals(1184.97, value, 0.001);
    }
}
