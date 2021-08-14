package com.example.demo.common;

public class BankFactory {

	public BankFactory() {
		// TODO Auto-generated constructor stub
	}
	
	 public static final Bank getBank(BankType bankType) {
	        switch (bankType) {
	 
	        case NCB:
	            return new NCBBank();
	 
	        default:
	            throw new IllegalArgumentException("This bank type is unsupported");
	        }
	    }

}
