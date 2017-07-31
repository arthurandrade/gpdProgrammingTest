package com.gpd.model.customer;

import com.gpd.rules.MoreThanX;
import com.gpd.rules.RuleDiscount;

public class Nike extends Customers {

    private RuleDiscount[] rules = { new MoreThanX("premium", 4, 379.99) };

    public Nike() {
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
