package com.gpd.model.customer;

import com.gpd.rules.RuleDiscount;

public abstract class Customers {

    protected RuleDiscount[] rules = {};

    public abstract RuleDiscount[] getRules();

    public abstract void setRules(RuleDiscount... rules);

}
