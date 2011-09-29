package com.thoughtworks.demo.taxes.impl;

import java.math.BigDecimal;

import com.thoughtworks.demo.taxes.ProductCategory;
import com.thoughtworks.demo.taxes.Tax;
import com.thoughtworks.demo.taxes.TaxManager;

/**
 * An implementation of the {@link TaxManager} interface that is able to manage
 * base sales taxes and import duties.
 * 
 * @author Dmitriy Efremov
 */
public class TaxManagerImpl implements TaxManager {
	
	private static final BigDecimal IMPORT_DUTY_RATE = new BigDecimal("0.05");

	@Override
	public Tax getTax(ProductCategory category, boolean isImported) {
		CompositeTax result = new CompositeTax();
		result.addTax(new FixedRateTax(category.getSalesTax()));
		if (isImported) {
			result.addTax(new FixedRateTax(IMPORT_DUTY_RATE));
		}
		return result;
	}


}
