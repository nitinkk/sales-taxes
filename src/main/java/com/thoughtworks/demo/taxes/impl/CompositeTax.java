package com.thoughtworks.demo.taxes.impl;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import com.thoughtworks.demo.taxes.Tax;

/**
 * An implementation of the {@link Tax} interface that works as a superposition
 * of multiple taxes.
 * 
 * @author Dmitriy Efremov
 */
public class CompositeTax implements Tax {
	
	private final List<Tax> taxes = new LinkedList<Tax>();
	
	/**
	 * Adds another tax to this composite.
	 * @param tax the tax to add
	 */
	public void addTax(Tax tax) {
		taxes.add(tax);
	}

	@Override
	public BigDecimal getTax(BigDecimal price) {
		BigDecimal result = BigDecimal.ZERO;
		for (Tax tax : taxes) {
			result = result.add(tax.getTax(price));
		}
		return result;
	}

}
