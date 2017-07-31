package com.gpd.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.gpd.model.checkout.Invoice;
import com.gpd.model.product.Product;
import com.gpd.service.CheckoutService;
import com.gpd.service.CheckoutServiceImpl;
import com.gpd.service.ProductService;
import com.gpd.service.ProductServiceImpl;

@RunWith(SpringRunner.class)
public class ProductDiscountTest {

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

        service.add(new ProductDiscount("premium", 389.99));
        service.addProductFor("premium");

        assertNotEquals(product.getPrice(), service.total());
        assertEquals(389.99, service.total(), 0.01);
    }

    @Test
    public void ShouldntGetDiscount() {

        Product product = productService.findBy("classic");

        service.add(new ProductDiscount("premium", 389.99));
        service.addProductFor(product.getId());

        assertNotEquals(389.99, service.total());
        assertEquals(product.getPrice(), service.total(), 0.01);
    }

    @Test
    public void ShouldntGetDiscountForOneProduct() {

        Product product = productService.findBy("classic");

        service.add(new ProductDiscount("premium", 389.99));
        service.addProductFor(product.getId());
        service.addProductFor("premium");

        Invoice invoice = service.getInvoice();

        assertEquals(2, invoice.getProducts().size());
        assertEquals(659.99, invoice.getTotal(), 0.01);
    }
}
