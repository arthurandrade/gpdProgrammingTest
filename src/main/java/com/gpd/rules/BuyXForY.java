package com.gpd.rules;

import java.util.List;
import java.util.stream.Collectors;

import com.gpd.model.checkout.Invoice;
import com.gpd.model.product.ProductCard;

public class BuyXForY extends RuleDiscount {

    private int x;
    private int y;
    private String idProduct;

    public BuyXForY(int x, int y, String idProduct) {
        this.x = x;
        this.y = y;
        this.idProduct = idProduct;
    }

    @Override
    public boolean isValid(Invoice invoice) {
        List<ProductCard> productDiscont = findProducts(invoice, idProduct);
        return productDiscont.size() >= x;
    }

    @Override
    public Invoice applyDiscount(Invoice invoice) {
        List<ProductCard> products = invoice.getProducts();
        invoice.setProducts(products.stream().map(p -> processProduct(p, idProduct)).collect(Collectors.toList()));
        return invoice;
    }

    @Override
    public double ruleDiscount(double price) {
        return ((x - y) * price) / x;
    }

}
