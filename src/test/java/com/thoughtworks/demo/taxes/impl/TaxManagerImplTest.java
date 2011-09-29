package com.thoughtworks.demo.taxes.impl;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.demo.taxes.ProductCategory;
import com.thoughtworks.demo.taxes.Tax;

public class TaxManagerImplTest {
	
	private TaxManagerImpl taxManager;

	@Before
	public void setUp() throws Exception {
		taxManager = new TaxManagerImpl();
	}
	
	@Test
	public void testGetTaxNone() {
		Tax tax = taxManager.getTax(ProductCategory.MEDICINE, false);
		assertEquals(new BigDecimal("0.00"), tax.getTax(new BigDecimal("100")));
	}	

	@Test
	public void testGetTaxBase() {
		Tax tax = taxManager.getTax(ProductCategory.OTHER, false);
		assertEquals(new BigDecimal("10.00"), tax.getTax(new BigDecimal("100")));
	}
	
	@Test
	public void testGetTaxImport() {
		Tax tax = taxManager.getTax(ProductCategory.MEDICINE, true);
		assertEquals(new BigDecimal("5.00"), tax.getTax(new BigDecimal("100")));
	}	
	
	@Test
	public void testGetTaxBaseAndImport() {
		Tax tax = taxManager.getTax(ProductCategory.OTHER, true);
		assertEquals(new BigDecimal("15.00"), tax.getTax(new BigDecimal("100")));
	}	

}
