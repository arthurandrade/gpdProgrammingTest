package com.gpd.model.customer;

import com.gpd.rules.ProductDiscount;
import com.gpd.rules.RuleDiscount;

public class Apple extends Customers {

    private RuleDiscount[] rules = { new ProductDiscount("standout", 299.99) };

    public Apple() {
    }

    @Override
    public RuleDiscount[] getRules() {
        return rules;
    }

    @Override
    public void setRules(RuleDiscount... rules) {
        this.rules = rules;
    }
}
