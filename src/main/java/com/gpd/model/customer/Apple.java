package com.gpd.model.customer;

import com.gpd.rules.ProductDiscount;
import com.gpd.rules.DiscountRule;

public class Apple extends Customers {

    private DiscountRule[] rules = { new ProductDiscount("standout", 299.99) };

    public Apple() {
    }

    @Override
    public DiscountRule[] getRules() {
        return rules;
    }

    @Override
    public void setRules(DiscountRule... rules) {
        this.rules = rules;
    }
}
