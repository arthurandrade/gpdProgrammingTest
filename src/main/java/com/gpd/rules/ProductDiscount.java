package com.gpd.rules;

import java.util.List;
import java.util.stream.Collectors;

import com.gpd.model.checkout.Invoice;
import com.gpd.model.product.CardProduct;

public class ProductDiscount extends DiscountRule {

    private String idProduct;
    private double newPrice;

    public ProductDiscount(String idProduct, double newPrice) {
        this.idProduct = idProduct;
        this.newPrice = newPrice;
    }

    @Override
    public boolean isValid(Invoice invoice) {
        List<CardProduct> eligibleProducts = findProducts(invoice, idProduct);
        return eligibleProducts.size() > 0;
    }

    @Override
    public Invoice processDiscount(Invoice invoice) {
        List<CardProduct> products = invoice.getProducts();
        invoice.setProducts(products.stream().map(p -> processProduct(p, idProduct)).collect(Collectors.toList()));
        return invoice;
    }

    @Override
    public double applyDiscount(double price) {
        return price - newPrice;
    }

}
