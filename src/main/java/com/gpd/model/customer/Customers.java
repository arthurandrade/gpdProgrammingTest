package com.gpd.model.customer;

import com.gpd.rules.DiscountRule;

public abstract class Customers {

    protected DiscountRule[] rules = {};

    public abstract DiscountRule[] getRules();

    public abstract void setRules(DiscountRule... rules);

}
