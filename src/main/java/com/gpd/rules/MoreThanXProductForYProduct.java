package com.gpd.rules;

import java.util.List;
import java.util.stream.Collectors;

import com.gpd.model.checkout.Invoice;
import com.gpd.model.product.CardProduct;

public class MoreThanXProductForYProduct extends DiscountRule {

    private String idProduct;
    private Integer quantity;
    private double newPrice;

    public MoreThanXProductForYProduct(String idProduct, Integer quantity, double newPrice) {
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.newPrice = newPrice;
    }

    @Override
    public boolean isValid(Invoice invoice) {
        List<CardProduct> eligibleProducts = findProducts(invoice, idProduct);
        return eligibleProducts.size() >= this.quantity;
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
    
    @Override
    public List<CardProduct> findProducts(Invoice invoice, String idProduct) {
        List<CardProduct> productDiscont = invoice.getProducts().stream()
                .filter(product -> product.getProduct().getId().contains(idProduct))
                .collect(Collectors.toList());
        return productDiscont;
    }

}
