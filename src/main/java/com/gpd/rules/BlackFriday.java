package com.gpd.rules;

import com.gpd.model.checkout.Invoice;

public class BlackFriday extends DiscountRule {

	public ApplyIn applyIn = ApplyIn.Cart;
	
	@Override
	public boolean isValid(Invoice invoice) {
		return invoice.getTotal() > 500;
	}

	@Override
	public Invoice processDiscount(Invoice invoice) {
		double discont = invoice.getTotal()/2;
		invoice.setDiscount(discont);
		return invoice;
	}

	@Override
	public double applyDiscount(double price) {
		// TODO Auto-generated method stub
		return 0;
	}

}
