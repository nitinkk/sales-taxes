package com.thoughtworks.demo.taxes;

import java.math.BigDecimal;

/**
 * This interface provides unified access to different taxation kinds.
 * 
 * @author Dmitriy Efremov
 */
public interface Tax {
	
	/**
	 * Calculates the tax value for the given price.
	 * @param price the price
	 * @return the tax value
	 */
	BigDecimal getTax(BigDecimal price);

}
