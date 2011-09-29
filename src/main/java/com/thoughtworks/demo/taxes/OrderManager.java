package com.thoughtworks.demo.taxes;

/**
 * This service is used to manage orders: create order, add item to the order,
 * checkout, etc.
 * 
 * @author Dmitriy Efremov
 */
public interface OrderManager {
	
	/**
	 * Creates a new order.
	 * @return the order id
	 */
	long createOrder();
	
	/**
	 * Cancels the specified order.
	 * @param orderId the order id
	 */
	void cancelOrder(long orderId);
	
	/**
	 * Adds the specified catalog item to the specified order.
	 * @param orderId the order id
	 * @param itemId the catalog item id
	 * @param quantity the number of items to add
	 * @throws NoSuchItemException if there is no order or catalog item found for the specified ids
	 */
	void addItem(long orderId, long itemId, int quantity);
	
	/**
	 * Checkouts the specified order. The order is no longer available for updates after this operation.
	 * @param orderId the order id
	 * @return the order
	 * @throws NoSuchItemException if there is no order found for the specified id
	 */
	Order checkout(long orderId);
	
}
