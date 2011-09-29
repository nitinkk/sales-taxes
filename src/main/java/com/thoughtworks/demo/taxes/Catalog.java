package com.thoughtworks.demo.taxes;

/**
 * This service is intended for read only catalog access.
 * 
 * @author Dmitriy Efremov
 */
public interface Catalog {
	
	/**
	 * Returns an item by its id.
	 * @param id the item's id
	 * @return the item
	 * @throws NoSuchItemException if there is no catalog item with the given id
	 */
	CatalogItem getItem(long id);

}
