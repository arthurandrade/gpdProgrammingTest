package com.gpd.model.customer;

import com.gpd.rules.BuyXProductsPayForY;
import com.gpd.rules.MoreThanXProductForYProduct;
import com.gpd.rules.ProductDiscount;
import com.gpd.rules.DiscountRule;

public class Ford extends Customers {
    private DiscountRule[] rules = { new BuyXProductsPayForY(5, 4, "classic"), new ProductDiscount("standout", 309.99),
            new MoreThanXProductForYProduct("premium", 3, 389.99) };

    public Ford() {
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
