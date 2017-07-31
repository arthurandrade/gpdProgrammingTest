package com.gpd.model.customer;

import com.gpd.rules.RuleDiscount;

public class Default extends Customers {

    public Default() {

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
