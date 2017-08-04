package com.gpd.model.customer;

import com.gpd.rules.BuyXProductsPayForY;
import com.gpd.rules.DiscountRule;

public class Unilever extends Customers {

    private DiscountRule[] rules = { new BuyXProductsPayForY(3, 2, "classic") };

    public Unilever() {
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
