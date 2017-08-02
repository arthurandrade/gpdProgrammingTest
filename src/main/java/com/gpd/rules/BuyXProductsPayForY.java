package com.gpd.rules;

import java.util.List;
import java.util.stream.Collectors;

import com.gpd.model.checkout.Invoice;
import com.gpd.model.product.CardProduct;

public class BuyXProductsPayForY extends DiscountRule {

    private int x;
    private int y;
    private String idProduct;

    public BuyXProductsPayForY(int x, int y, String idProduct) {
        this.x = x;
        this.y = y;
        this.idProduct = idProduct;
    }

    @Override
    public boolean isValid(Invoice invoice) {
        List<CardProduct> eligibleProducts = findProducts(invoice, idProduct);
        return eligibleProducts.size() >= x;
    }

    @Override
    public Invoice processDiscount(Invoice invoice) {
        List<CardProduct> products = invoice.getProducts();
        invoice.setProducts(products.stream().map(p -> processProduct(p, idProduct)).collect(Collectors.toList()));
        return invoice;
    }

    @Override
    public double applyDiscount(double price) {
        return ((x - y) * price) / x;
    }

}
