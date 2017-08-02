package com.gpd.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpd.model.checkout.Invoice;
import com.gpd.model.product.Product;
import com.gpd.model.product.CardProduct;
import com.gpd.rules.DiscountRule;

@Service("checkoutService")
public class CheckoutServiceImpl implements CheckoutService {

    List<DiscountRule> rules = new ArrayList<DiscountRule>();
    Invoice invoice = new Invoice();

    @Autowired
    ProductService productService;

    @Override
    public void addProductFor(String id) {
        Product newProduct = productService.findBy(id);
        if (newProduct == null) {
            throw new RuntimeException("Product not found!");
        }
        invoice.addProduct(new CardProduct(newProduct));
        calculateDiscount();
        invoice.calculateTotal();
    }

    @Override
    public double total() {
        return invoice.getTotal();
    }

    private void calculateDiscount() {
        rules.forEach(rule -> {
            if (rule.isValid(invoice)) {
                invoice = rule.processDiscount(invoice);
            }
        });
    }

    @Override
    public void add(DiscountRule... rules) {
        this.rules.addAll(Arrays.asList(rules));
    }

    @Override
    public Invoice getInvoice() {
        return invoice;
    }

    @Override
    public void clear() {
        invoice = new Invoice();
        rules.clear();
    }

}
