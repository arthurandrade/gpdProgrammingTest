package com.gpd.model.customer;

import com.gpd.rules.MoreThanXProductForYProduct;
import com.gpd.rules.DiscountRule;

public class Nike extends Customers {

    private DiscountRule[] rules = { new MoreThanXProductForYProduct("premium", 4, 379.99) };

    public Nike() {
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
