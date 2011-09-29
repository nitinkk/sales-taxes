package com.thoughtworks.demo.taxes.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.thoughtworks.demo.taxes.Tax;

/**
 * A fixed rate implementation of the {@link Tax} interface. It rounds taxes up
 * to the nearest 0.05.
 * 
 * @author Dmitriy Efremov
 */
public class FixedRateTax implements Tax {
	
	private static final BigDecimal TICK_SIZE = new BigDecimal("0.05");
	
	private final BigDecimal rate;
	
	/**
	 * Creates a tax with the specified rate.
	 * @param rate the tax rate
	 */
	public FixedRateTax(BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public BigDecimal getTax(BigDecimal price) {
		return roundOff(price.multiply(rate));
	}
	
	private static BigDecimal roundOff(BigDecimal value) {
		return value.divide(TICK_SIZE).setScale(0, RoundingMode.UP).multiply(TICK_SIZE);
	}

}
