package com.thoughtworks.demo.taxes.impl;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class FixedRateTaxTest {

	@Test
	public void testZeroTax() {
		FixedRateTax tax = new FixedRateTax(new BigDecimal(0));
		assertEquals(new BigDecimal("0.00"), tax.getTax(new BigDecimal(100)));
	}
	
	@Test
	public void testFixedTax() {
		FixedRateTax tax = new FixedRateTax(new BigDecimal("0.1"));
		assertEquals(new BigDecimal("10.00"), tax.getTax(new BigDecimal(100)));
	}
	
	@Test
	public void testRounding() {
		FixedRateTax tax = new FixedRateTax(new BigDecimal("0.05"));
		assertEquals(new BigDecimal("2.40"), tax.getTax(new BigDecimal("47.5")));
	}

}
