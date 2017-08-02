package com.gpd.rules;

import java.util.List;
import java.util.stream.Collectors;

import com.gpd.model.checkout.Invoice;
import com.gpd.model.product.CardProduct;
import com.gpd.utils.DoubleUtils;

public abstract class DiscountRule {

    public abstract boolean isValid(Invoice invoice);

    public abstract Invoice processDiscount(Invoice invoice);

    public abstract double applyDiscount(double price);

    public List<CardProduct> findProducts(Invoice invoice, String idProduct) {
        List<CardProduct> eligibleProducts = invoice.getProducts().stream()
                .filter(product -> product.getProduct().getId().contains(idProduct) && product.isApplied() == false)
                .collect(Collectors.toList());
        return eligibleProducts;
    }

    public CardProduct processProduct(CardProduct product, String idProduct) {
        if (product.getProduct().getId().contains(idProduct) && !product.isApplied()) {
            double discount = DoubleUtils.round3(applyDiscount(product.getPrice()));
            product.setDiscount(discount);
            product.setApplied(true);
        }
        return product;
    }
}
