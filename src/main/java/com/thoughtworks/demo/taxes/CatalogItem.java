package com.thoughtworks.demo.taxes;

import java.math.BigDecimal;

/**
 * Catalog item abstraction. Contains base properties of an item in the catalog.
 * Immutable.
 * 
 * @author Dmitriy Efremov
 */
public class CatalogItem {
	
	private final long id;
	
	private final String title;
	
	private final BigDecimal price;
	
	private final ProductCategory category;
	
	private final boolean imported;
	
	/**
	 * Constructor initializing all fields.
	 * @param id the item id
	 * @param title the item title
	 * @param price the item price
	 * @param category the product category
	 * @param imported the the flag defining if the item is imported or not
	 */
	public CatalogItem(long id, String title, BigDecimal price, ProductCategory category, boolean imported) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.category = category;
		this.imported = imported;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public boolean isImported() {
		return imported;
	}

	public ProductCategory getCategory() {
		return category;
	}
	
}
