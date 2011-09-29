package com.thoughtworks.demo.taxes.impl;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Trivial unique id generator. It just holds an id value and increments it each
 * time.
 * 
 * @author Dmitriy Efremov
 */
class IdGenerator {

	private final AtomicLong nextId = new AtomicLong(1);
	
	/**
	 * Returns the next id value.
	 * @return the id
	 */
	public long getId() {
		return nextId.getAndIncrement();
	}
	
}
