package com.gpd.rules;

import static org.junit.Assert.assertEquals;

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
public class BuyXForYTest {

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

        service.add(new BuyXForY(3, 2, "premium"));
        service.addProductFor("premium");
        service.addProductFor("premium");
        service.addProductFor("premium");

        assertEquals(2 * product.getPrice(), service.total(), 0.01);
    }

    @Test
    public void ShouldGetDiscountOnce() {

        Product product = productService.findBy("premium");

        service.add(new BuyXForY(3, 2, "premium"));
        service.addProductFor("premium");
        service.addProductFor("premium");
        service.addProductFor("premium");
        assertEquals(2 * product.getPrice(), service.total(), 0.01);

        service.addProductFor("premium");
        Invoice invoice = service.getInvoice();

        assertEquals(4, invoice.getProducts().size());
        assertEquals(3 * product.getPrice(), invoice.getTotal(), 0.01);
    }

    @Test
    public void ShouldGetDiscountTwice() {

        Product product = productService.findBy("premium");

        service.add(new BuyXForY(3, 2, "premium"));
        service.addProductFor("premium");
        service.addProductFor("premium");
        service.addProductFor("premium");
        assertEquals(2 * product.getPrice(), service.total(), 0.01);

        service.addProductFor("premium");
        service.addProductFor("premium");
        service.addProductFor("premium");
        Invoice invoice = service.getInvoice();

        assertEquals(6, invoice.getProducts().size());
        assertEquals(4 * product.getPrice(), invoice.getTotal(), 0.01);
    }
}
