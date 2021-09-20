package com.example.demo.common;

public class CalculateDiscount {

	public static Double countDiscount(Long price, Long list_price) {
		
		Double percent = (list_price.doubleValue() - price.doubleValue()) / list_price.doubleValue()
				* 100;
		return (double) Math.round(percent * 10) / 10;
	}

}
