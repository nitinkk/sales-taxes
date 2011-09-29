package com.thoughtworks.demo.taxes.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.demo.taxes.Catalog;
import com.thoughtworks.demo.taxes.CatalogItem;
import com.thoughtworks.demo.taxes.CatalogManager;
import com.thoughtworks.demo.taxes.NoSuchItemException;
import com.thoughtworks.demo.taxes.ProductCategory;

/**
 * Trivial in-memory implementation of the {@link Catalog} interface. It holds a
 * map {@link CatalogItem} objects using their ids as keys.
 * 
 * @author Dmitriy Efremov
 */
public class TrivialCatalogImpl implements Catalog, CatalogManager {
	
	private final Map<Long, CatalogItem> items = new HashMap<Long, CatalogItem>();
	
	private final IdGenerator idGenerator = new IdGenerator();
	
	@Override
	public synchronized long addItem(String title, BigDecimal price, ProductCategory category, boolean imported) {
		long id = idGenerator.getId();
		CatalogItem item = new CatalogItem(id, title, price, category, imported);
		items.put(id, item);
		return id;
	}

	@Override
	public synchronized void removeItem(long id) {
		if (items.remove(id) == null) {
			throw new NoSuchItemException("There is no catalog item: " + id);
		}
	}

	@Override
	public synchronized CatalogItem getItem(long id) {
		CatalogItem item = items.get(id);
		if (item == null) {
			throw new NoSuchItemException("There is no catalog item: " + id);
		}
		return item;
	}
	
}
