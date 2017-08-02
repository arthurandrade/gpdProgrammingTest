package com.gpd.rules;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
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
public class MoreThanXProductForYProductTest {

    Product product;

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

    @Before
    public void DefinePrices() {
        product = productService.findBy("premium");
    }

    @Test
    public void ShouldntGetDiscount() {

        service.add(new MoreThanXProductForYProduct("premium", 3, 389.99));
        service.addProductFor("premium");
        service.addProductFor("premium");
        assertEquals(2 * product.getPrice(), service.total(), 0.001);
    }

    @Test
    public void ShouldGetDiscount3Products() {

        service.add(new MoreThanXProductForYProduct("premium", 3, 389.99));
        service.addProductFor("premium");
        service.addProductFor("premium");
        service.addProductFor("premium");
        assertEquals(3 * 389.99, service.total(), 0.001);
    }
    
    @Test
    public void ShouldGetDiscount4Products() {

        service.add(new MoreThanXProductForYProduct("premium", 3, 389.99));
        service.addProductFor("premium");
        service.addProductFor("premium");
        service.addProductFor("premium");
        service.addProductFor("premium");
        assertEquals(4 * 389.99, service.total(), 0.001);
    }    

    @Test
    public void ShouldGetDiscountFor6Products() {

        service.add(new MoreThanXProductForYProduct("premium", 3, 389.99));
        service.addProductFor("premium");
        service.addProductFor("premium");
        service.addProductFor("premium");
        service.addProductFor("premium");
        service.addProductFor("premium");
        service.addProductFor("premium");

        Invoice invoice = service.getInvoice();

        assertEquals(6, invoice.getProducts().size());
        assertEquals(6 * 389.99, invoice.getTotal(), 0.001);
    }
}
