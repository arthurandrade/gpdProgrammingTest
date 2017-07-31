package com.gpd.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import com.gpd.model.product.Product;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    private List<Product> products;

    public ProductServiceImpl() {
        products = findAllProducts();
    }

    @Override
    public Product findBy(String id) {
        Optional<Product> product = products.stream().filter(prod -> prod.getId().contains(id)).findFirst();
        return product.orElse(null);
    }

    @Override
    public List<Product> findAllProducts() {
        final List<Product> products = new ArrayList<Product>();
        try (Stream<String> stream = Files.lines(Paths.get(getClass().getResource("/products.txt").toURI()))) {
            stream.forEachOrdered(line -> {
                String[] lineprosses = line.split("\\|");
                products.add(new Product(lineprosses[0], lineprosses[1], Double.parseDouble(lineprosses[2])));
            });
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return products;
    }
}
