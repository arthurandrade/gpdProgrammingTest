package com.gpd.rules;

import java.util.List;
import java.util.stream.Collectors;

import com.gpd.model.checkout.Invoice;
import com.gpd.model.product.ProductCard;
import com.gpd.utils.DoubleUtils;

public abstract class RuleDiscount {

    public abstract boolean isValid(Invoice invoice);

    public abstract Invoice applyDiscount(Invoice invoice);

    public abstract double ruleDiscount(double price);

    public List<ProductCard> findProducts(Invoice invoice, String idProduct) {
        List<ProductCard> productDiscont = invoice.getProducts().stream()
                .filter(product -> product.getProduct().getId().contains(idProduct) && product.isApplied() == false)
                .collect(Collectors.toList());
        return productDiscont;
    }

    public ProductCard processProduct(ProductCard product, String idProduct) {
        if (product.getProduct().getId().contains(idProduct) && !product.isApplied()) {
            double discount = DoubleUtils.round3(ruleDiscount(product.getPrice()));
            product.setDiscount(discount);
            product.setApplied(true);
        }
        return product;
    }
}
