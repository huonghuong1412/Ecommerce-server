package com.example.demo.common;

public class Constants {
	public static enum ProductType {

		Book(1), // sach
		Clothes(2), // quan ao
		Electric(3), // do dien tu
		;

		private Integer value;

		private ProductType(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

}
