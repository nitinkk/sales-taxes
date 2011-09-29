package com.thoughtworks.demo.taxes.impl;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class CompositeTaxTest {
	
	private CompositeTax tax;
	
	@Before
	public void setup() {
		tax = new CompositeTax();
	}
	
	@Test
	public void testEmptyGetTax() {
		assertEquals(new BigDecimal("0"), tax.getTax(new BigDecimal("47.5")));
	}	

	@Test
	public void testGetTax() {
		tax.addTax(new FixedRateTax(new BigDecimal("0.1")));
		tax.addTax(new FixedRateTax(new BigDecimal("0.05")));
		assertEquals(new BigDecimal("7.15"), tax.getTax(new BigDecimal("47.5")));
	}

}
