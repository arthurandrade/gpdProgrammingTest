package com.gpd.service;

import com.gpd.model.checkout.Invoice;
import com.gpd.rules.RuleDiscount;

public interface CheckoutService {

    public void addProductFor(String id);

    public double total();

    public void add(RuleDiscount... rules);

    public Invoice getInvoice();

    void clear();
}
