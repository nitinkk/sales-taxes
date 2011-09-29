package com.thoughtworks.demo.taxes;

/**
 * Defines tax rates for catalog items.
 * 
 * @author Dmitriy Efremov
 */
public interface TaxManager {
	
	/**
	 * Calculates a tax rate for the specified item's attributes.
	 * @param category the product category
	 * @param isImported the flag defining if the product was imported
	 * @return the {@link Tax} object to be applied
	 */
	Tax getTax(ProductCategory category, boolean isImported);

}
