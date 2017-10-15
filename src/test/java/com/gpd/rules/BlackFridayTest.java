package com.gpd.rules;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.gpd.model.product.Product;
import com.gpd.service.CheckoutService;
import com.gpd.service.CheckoutServiceImpl;
import com.gpd.service.ProductService;
import com.gpd.service.ProductServiceImpl;

@RunWith(SpringRunner.class)
public class BlackFridayTest {
	
	Product otheProduct;

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

    @Autowired
    private ProductService productService;

    @After
    public void cleanCart() {
        service.clear();
    }

    @Test
    public void ShouldGetDiscount() {

        Product product = productService.findBy("premium");

        service.add(new BuyXProductsPayForY(3, 2, "premium"), new BlackFriday());
        service.addProductFor("premium");
        service.addProductFor("premium");
        service.addProductFor("premium");

        assertEquals(2 * product.getPrice(), service.total(), 0.01);
    }

}
