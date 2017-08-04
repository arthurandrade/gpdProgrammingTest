package com.gpd.model.customer;

import com.gpd.rules.DiscountRule;

public class Default extends Customers {

    public Default() {

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
