package com.thoughtworks.demo.taxes;

import java.math.BigDecimal;

/**
 * Order item abstraction. Contains a {@link CatalogItem} and an applied tax
 * value. Immutable.
 * 
 * @author Dmitriy Efremov
 */
public class OrderItem {
	
	private final CatalogItem catalogItem;
	
	private final BigDecimal tax;
	
	private final int quantity;
	
	private final BigDecimal total;
	
	private final BigDecimal totalTax;
	
	/**
	 * Constructor.
	 * @param catalogItem the catalog item
	 * @param tax the value of applied tax
	 * @param quantity the number of catalog items (positive number)
	 */
	public OrderItem(CatalogItem catalogItem, BigDecimal tax, int quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("Quantity shall be positive");
		}
		this.catalogItem = catalogItem;
		this.tax = tax;
		this.quantity = quantity;
		this.totalTax = tax.multiply(new BigDecimal(quantity));		
		this.total = catalogItem.getPrice().multiply(new BigDecimal(quantity)).add(totalTax);

	}

	public CatalogItem getCatalogItem() {
		return catalogItem;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public BigDecimal getTotal() {
		return total;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	@Override
	public String toString() {
		return quantity + " " + catalogItem.getTitle() + " : " + total;
	}
	
}
