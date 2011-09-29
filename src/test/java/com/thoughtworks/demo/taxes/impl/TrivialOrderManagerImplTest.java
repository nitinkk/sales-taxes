package com.thoughtworks.demo.taxes.impl;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.demo.taxes.CatalogManager;
import com.thoughtworks.demo.taxes.NoSuchItemException;
import com.thoughtworks.demo.taxes.Order;
import com.thoughtworks.demo.taxes.OrderItem;
import com.thoughtworks.demo.taxes.ProductCategory;

public class TrivialOrderManagerImplTest {
	
	private TrivialOrderManagerImpl orderManager;
	
	private CatalogManager catalogManager;	

	@Before
	public void setUp() throws Exception {
		TrivialCatalogImpl catalogImpl = new TrivialCatalogImpl();
		catalogManager = catalogImpl;
		orderManager = new TrivialOrderManagerImpl();
		orderManager.setTaxManager(new TaxManagerImpl());
		orderManager.setCatalog(catalogImpl);		
	}

	@Test
	public void testCreateOrder() {
		long id1 = orderManager.createOrder();
		long id2 = orderManager.createOrder();
		assertFalse(id1 == id2);
	}
	
	@Test(expected = NoSuchItemException.class)
	public void testCancelNonExistentOrder() {
		orderManager.cancelOrder(0);
	}
	
	@Test
	public void testCancelOrder() {
		long id = orderManager.createOrder();
		orderManager.cancelOrder(id);
		try {
			orderManager.checkout(id);
			fail("the order was not removed");
		} catch (NoSuchItemException e) {
			// the order removed successfully
		}
	}	

	@Test(expected = IllegalArgumentException.class)
	public void testAddItemWrongQuantity() {
		long orderId = orderManager.createOrder();
		long itemId = catalogManager.addItem("title", BigDecimal.ZERO, ProductCategory.OTHER, false);
		orderManager.addItem(orderId, itemId, 0);
	}
	
	@Test
	public void testAddItem() {
		long orderId = orderManager.createOrder();
		long itemId = catalogManager.addItem("title", BigDecimal.ZERO, ProductCategory.OTHER, false);
		orderManager.addItem(orderId, itemId, 2);
		Order order = orderManager.checkout(orderId);
		assertEquals(1, order.getOrderItems().size());
		assertEquals(2, order.getOrderItems().get(0).getQuantity());
	}
	
	@Test(expected = NoSuchItemException.class)
	public void testAddItemToWrongOrder() {
		long itemId = catalogManager.addItem("title", BigDecimal.ZERO, ProductCategory.OTHER, false);
		orderManager.addItem(0, itemId, 1);
	}
	
	@Test(expected = NoSuchItemException.class)
	public void testAddItemWrongItem() {
		long orderId = orderManager.createOrder();
		orderManager.addItem(orderId, 0, 1);
	}	

	@Test
	public void testCheckout() {
		long orderId = orderManager.createOrder();
		long itemId = catalogManager.addItem("title", new BigDecimal("100"), ProductCategory.OTHER, true);
		orderManager.addItem(orderId, itemId, 1);
		Order order = orderManager.checkout(orderId);
		assertEquals(1, order.getOrderItems().size());
		OrderItem orderItem = order.getOrderItems().get(0);
		assertEquals(new BigDecimal("15.00"), orderItem.getTax());
	}

}
