package com.thoughtworks.demo.taxes;

import java.math.BigDecimal;

/**
 * This service provides methods for catalog modification.
 * 
 * @author Dmitriy Efremov
 */
public interface CatalogManager {
	
	/**
	 * Adds a new item.
	 * @param title the item's title
	 * @param price the item's price
	 * @param category the product category
	 * @param imported the flag defining if the item is imported or not
	 * @return the added item's id
	 */
	long addItem(String title, BigDecimal price, ProductCategory category, boolean imported);
	
	/**
	 * Removes the specified item.
	 * @param id the item's id
	 * @throws NoSuchItemException if there is no catalog item with the given id
	 */
	void removeItem(long id);

}
