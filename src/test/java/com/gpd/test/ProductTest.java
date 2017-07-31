package com.gpd.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.gpd.model.product.Product;
import com.gpd.service.ProductService;
import com.gpd.service.ProductServiceImpl;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class ProductTest {

    @TestConfiguration
    static class ProductServiceImplTestConfiguration {
        @Bean
        public ProductService service() {
            return new ProductServiceImpl();
        }
    }

    @Autowired
    private ProductService service;

    @Test
    public void ShouldGetAllProducts() {
        List<Product> test = service.findAllProducts();
        assertEquals(3, test.size());
    }

    @Test
    public void ShouldGetClassicProduct() {
        Product test = service.findBy("classic");
        assertEquals("classic", test.getId());
    }

    @Test
    public void ShouldGetStandoutProduct() {
        Product test = service.findBy("standout");
        assertEquals("standout", test.getId());
    }

    @Test
    public void ShouldGetProductNotRegistered() {
        Product test = service.findBy("NotRegistered");
        assertEquals(null, test);
    }

}
