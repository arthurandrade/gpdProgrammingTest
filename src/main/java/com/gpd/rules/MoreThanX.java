package com.gpd.rules;

import java.util.List;
import java.util.stream.Collectors;

import com.gpd.model.checkout.Invoice;
import com.gpd.model.product.ProductCard;

public class MoreThanX extends RuleDiscount {

    private String idProduct;
    private Integer quantity;
    private double newPrice;

    public MoreThanX(String idProduct, Integer quantity, double newPrice) {
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.newPrice = newPrice;
    }

    @Override
    public boolean isValid(Invoice invoice) {
        List<ProductCard> productDiscont = findProducts(invoice, idProduct);
        return productDiscont.size() >= this.quantity;
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
    
    @Override
    public List<ProductCard> findProducts(Invoice invoice, String idProduct) {
        List<ProductCard> productDiscont = invoice.getProducts().stream()
                .filter(product -> product.getProduct().getId().contains(idProduct))
                .collect(Collectors.toList());
        return productDiscont;
    }

}
