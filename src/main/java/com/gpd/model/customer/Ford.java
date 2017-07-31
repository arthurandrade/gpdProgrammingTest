package com.gpd.model.customer;

import com.gpd.rules.BuyXForY;
import com.gpd.rules.MoreThanX;
import com.gpd.rules.ProductDiscount;
import com.gpd.rules.RuleDiscount;

public class Ford extends Customers {
    private RuleDiscount[] rules = { new BuyXForY(5, 4, "classic"), new ProductDiscount("standout", 309.99),
            new MoreThanX("premium", 3, 389.99) };

    public Ford() {
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
