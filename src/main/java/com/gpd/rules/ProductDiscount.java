package com.gpd.rules;

import java.util.List;
import java.util.stream.Collectors;

import com.gpd.model.checkout.Invoice;
import com.gpd.model.product.ProductCard;

public class ProductDiscount extends RuleDiscount {

    private String idProduct;
    private double newPrice;

    public ProductDiscount(String idProduct, double newPrice) {
        this.idProduct = idProduct;
        this.newPrice = newPrice;
    }

    @Override
    public boolean isValid(Invoice invoice) {
        List<ProductCard> productDiscont = findProducts(invoice, idProduct);
        return productDiscont.size() > 0;
    }

    @Override
    public Invoice applyDiscount(Invoice invoice) {
        List<ProductCard> products = invoice.getProducts();
        invoice.setProducts(products.stream().map(p -> processProduct(p, idProduct)).collect(Collectors.toList()));
        return invoice;
    }

    @Override
    public double ruleDiscount(double price) {
        return price - newPrice;
    }

}
