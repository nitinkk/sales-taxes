package com.thoughtworks.demo;

import java.math.BigDecimal;

import com.thoughtworks.demo.taxes.CatalogManager;
import com.thoughtworks.demo.taxes.Order;
import com.thoughtworks.demo.taxes.OrderManager;
import com.thoughtworks.demo.taxes.ProductCategory;
import com.thoughtworks.demo.taxes.impl.TaxManagerImpl;
import com.thoughtworks.demo.taxes.impl.TrivialCatalogImpl;
import com.thoughtworks.demo.taxes.impl.TrivialOrderManagerImpl;

/**
 * Demo program illustrating basic concepts of "sales taxes" problem solution.
 * 
 * @author Dmitriy Efremov
 */
public class TaxesDemo {
	
	private CatalogManager catalogManager;
	
	private OrderManager orderManager;
	
	public void setCatalogManager(CatalogManager catalogManager) {
		this.catalogManager = catalogManager;
	}

	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}

	public void demoCase1() {
		// prepare all needed goods
		long bookId = catalogManager.addItem("book", new BigDecimal("12.49"), ProductCategory.BOOK, false);
		long cdId = catalogManager.addItem("music CD", new BigDecimal("14.99"), ProductCategory.MUSIC, false);
		long chocolateId = catalogManager.addItem("chocolate bar", new BigDecimal("0.85"), ProductCategory.FOOD, false);
		// order these goods
		long orderId = orderManager.createOrder();
		orderManager.addItem(orderId, bookId, 1);
		orderManager.addItem(orderId, cdId, 1);
		orderManager.addItem(orderId, chocolateId, 1);
		Order order = orderManager.checkout(orderId);
		// print receipt
		System.out.println(order);
	}
	
	public void demoCase2() {
		// prepare all needed goods
		long chocolateId = catalogManager.addItem("imported box of chocolates", new BigDecimal("10.00"), ProductCategory.FOOD, true);
		long perfumeId = catalogManager.addItem("imported bottle of perfume", new BigDecimal("47.50"), ProductCategory.PERFUME, true);
		// order these goods
		long orderId = orderManager.createOrder();
		orderManager.addItem(orderId, chocolateId, 1);
		orderManager.addItem(orderId, perfumeId, 1);
		Order order = orderManager.checkout(orderId);
		// print receipt
		System.out.println(order);		
	}
	
	public void demoCase3() {
		// prepare all needed goods
		long perfumeId = catalogManager.addItem("imported bottle of perfume", new BigDecimal("27.99"), ProductCategory.PERFUME, true);
		long anotherPerfumeId = catalogManager.addItem("bottle of perfume", new BigDecimal("18.99"), ProductCategory.PERFUME, false);
		long pillsId = catalogManager.addItem("packet of headache pills", new BigDecimal("9.75"), ProductCategory.MEDICINE, false);
		long chocolateId = catalogManager.addItem("imported box of chocolates", new BigDecimal("11.25"), ProductCategory.FOOD, true);		
		// order these goods
		long orderId = orderManager.createOrder();
		orderManager.addItem(orderId, perfumeId, 1);
		orderManager.addItem(orderId, anotherPerfumeId, 1);
		orderManager.addItem(orderId, pillsId, 1);
		orderManager.addItem(orderId, chocolateId, 1);
		Order order = orderManager.checkout(orderId);
		// print receipt
		System.out.println(order);
	}	

	public static void main(String[] args) {
		// create services and wire them
		TaxManagerImpl taxManagerImpl = new TaxManagerImpl();
		TrivialCatalogImpl catalogImpl = new TrivialCatalogImpl();
		TrivialOrderManagerImpl orderManagerImpl = new TrivialOrderManagerImpl();
		orderManagerImpl.setTaxManager(taxManagerImpl);
		orderManagerImpl.setCatalog(catalogImpl);
		// create demo
		TaxesDemo demo = new TaxesDemo();
		demo.setCatalogManager(catalogImpl);
		demo.setOrderManager(orderManagerImpl);
		// run demo cases
		demo.demoCase1();
		demo.demoCase2();
		demo.demoCase3();
	}

}
