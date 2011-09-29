package com.thoughtworks.demo.taxes;

import java.math.BigDecimal;

/**
 * Enumeration of product categories. Every category encapsulates its sales tax
 * rate.
 * 
 * @author Dmitriy Efremov
 */
public enum ProductCategory {
	
	/**
	 * Books category. No sales tax.
	 */
	BOOK ("0"),

	/**
	 * Food category. No sales tax.
	 */
	FOOD ("0"),
	
	/**
	 * Medical products category. No sales tax.
	 */
	MEDICINE ("0"),

	/**
	 * Music products category.
	 */
	MUSIC,
	
	/**
	 * Perfume products category.
	 */
	PERFUME,	
	
	/**
	 * All other goods.
	 */
	OTHER;
	
	private final BigDecimal salesTax;

	/**
	 * Default constructor for product categories those have basic sales tax rate. 
	 */
	private ProductCategory() {
		this("0.1");
	}	
	
	/**
	 * This constructor defines sales tax rate for a product category.
	 * @param salesTax the sales tax rate
	 */
	private ProductCategory(String salesTax) {
		this.salesTax = new BigDecimal(salesTax);
	}

	/**
	 * Returns sales tax rate of this product category.
	 * @return sales tax rate value
	 */
	public BigDecimal getSalesTax() {
		return salesTax;
	}
	
}
