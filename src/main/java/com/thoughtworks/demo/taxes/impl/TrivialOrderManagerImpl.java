package com.thoughtworks.demo.taxes.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.thoughtworks.demo.taxes.Catalog;
import com.thoughtworks.demo.taxes.CatalogItem;
import com.thoughtworks.demo.taxes.NoSuchItemException;
import com.thoughtworks.demo.taxes.Order;
import com.thoughtworks.demo.taxes.OrderItem;
import com.thoughtworks.demo.taxes.OrderManager;
import com.thoughtworks.demo.taxes.Tax;
import com.thoughtworks.demo.taxes.TaxManager;

/**
 * Trivial in-memory implementation of the {@link OrderManager} interface. It
 * holds a map {@link Order} objects using their ids as keys.
 * 
 * @author Dmitriy Efremov
 */
public class TrivialOrderManagerImpl implements OrderManager {
	
	private final HashMap<Long, List<OrderItem>> orders = new HashMap<Long, List<OrderItem>>();
	
	private final IdGenerator idGenerator = new IdGenerator();
	
	private Catalog catalog;
	
	private TaxManager taxManager;
	
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	
	public void setTaxManager(TaxManager taxManager) {
		this.taxManager = taxManager;
	}

	@Override
	public synchronized long createOrder() {
		long id = idGenerator.getId();
		List<OrderItem> orderItems = new LinkedList<OrderItem>();
		orders.put(id, orderItems);
		return id;
	}
	
	@Override
	public synchronized void cancelOrder(long orderId) {
		List<OrderItem> orderItems = orders.remove(orderId);
		if (orderItems == null) {
			throw new NoSuchItemException("There is no order: " + orderId);
		}
	}	

	@Override
	public synchronized void addItem(long orderId, long itemId, int quantity) {
		List<OrderItem> orderItems = orders.get(orderId);
		if (orderItems == null) {
			throw new NoSuchItemException("There is no order: " + orderId);
		}
		// create order item
		CatalogItem catalogItem = catalog.getItem(itemId);
		Tax tax = taxManager.getTax(catalogItem.getCategory(), catalogItem.isImported());
		OrderItem orderItem = new OrderItem(catalogItem, tax.getTax(catalogItem.getPrice()), quantity);
		orderItems.add(orderItem);
	}

	@Override
	public synchronized Order checkout(long orderId) {
		List<OrderItem> orderItems = orders.remove(orderId);
		if (orderItems == null) {
			throw new NoSuchItemException("There is no order: " + orderId);
		}
		return new Order(orderId, orderItems);
	}
	
}
