package com.thoughtworks.demo.taxes;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A abstraction of an order. Contains ordered items. Immutable.
 * 
 * @author Dmitriy Efremov
 */
public class Order {
	
	private final long id;
	
	private final List<OrderItem> orderItems = new LinkedList<OrderItem>();
	
	private final BigDecimal salesTaxes;
	
	private final BigDecimal total;
	
	/**
	 * Constructor.
	 * @param id the order's id
	 */
	public Order(long id, List<OrderItem> orderItems) {
		this.id = id;
		this.orderItems.addAll(orderItems);
		// calculate taxes and total
		BigDecimal salesTaxes = BigDecimal.ZERO;
		BigDecimal total = BigDecimal.ZERO;
		for (OrderItem orderItem : orderItems) {
			salesTaxes = salesTaxes.add(orderItem.getTotalTax());
			total = total.add(orderItem.getTotal());
		}
		this.salesTaxes = salesTaxes;
		this.total = total;
	}
	

	public long getId() {
		return id;
	}

	public List<OrderItem> getOrderItems() {
		return Collections.unmodifiableList(orderItems);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Order ").append(id).append(":\n");
		for (OrderItem orderItem : orderItems) {
			sb.append(orderItem).append('\n');
		}
		sb.append("Sales taxes: ").append(salesTaxes).append('\n');
		sb.append("Total: ").append(total).append('\n');
		return sb.toString();
	}

}
