package com.thoughtworks.demo.taxes.impl;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.demo.taxes.CatalogItem;
import com.thoughtworks.demo.taxes.NoSuchItemException;
import com.thoughtworks.demo.taxes.ProductCategory;

public class TrivialCatalogImplTest {
	
	private TrivialCatalogImpl catalogImpl;

	@Before
	public void setUp() throws Exception {
		catalogImpl = new TrivialCatalogImpl();
	}

	@Test
	public void testAddItem() {
		long id1 = catalogImpl.addItem("title1", new BigDecimal(1), ProductCategory.OTHER, false);
		long id2 = catalogImpl.addItem("title2", new BigDecimal(2), ProductCategory.OTHER, false);
		assertFalse(id1 == id2);
	}

	@Test
	public void testRemoveItem() {
		long id = catalogImpl.addItem("title", new BigDecimal(1), ProductCategory.OTHER, false);
		catalogImpl.removeItem(id);
		try {
			catalogImpl.getItem(id);
			fail("the item was not removed");
		} catch (NoSuchItemException e) {
			// the item removed successfully
		}
	}
	
	@Test(expected = NoSuchItemException.class)
	public void testRemoveAbsentItem() {	
		catalogImpl.removeItem(0);
	}

	@Test
	public void testGetItem() {
		long id = catalogImpl.addItem("title", new BigDecimal(1), ProductCategory.OTHER, false);
		CatalogItem item = catalogImpl.getItem(id);
		assertEquals(id, item.getId());
		assertEquals("title", item.getTitle());
		assertEquals(new BigDecimal(1), item.getPrice());
		assertSame(ProductCategory.OTHER, item.getCategory());
		assertFalse(item.isImported());
	}
	
	@Test(expected = NoSuchItemException.class)
	public void testGetAbsentItem() {
		catalogImpl.getItem(0);
	}

}
