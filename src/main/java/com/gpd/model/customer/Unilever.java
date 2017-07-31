package com.gpd.model.customer;

import com.gpd.rules.BuyXForY;
import com.gpd.rules.RuleDiscount;

public class Unilever extends Customers {

    private RuleDiscount[] rules = { new BuyXForY(3, 2, "classic") };

    public Unilever() {
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
