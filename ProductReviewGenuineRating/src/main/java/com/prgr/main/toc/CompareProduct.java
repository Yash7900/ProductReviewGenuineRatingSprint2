package com.prgr.main.toc;

import com.prgr.main.entity.Product;

public class CompareProduct {

	private Product product1;
	
	private Product product2;

	public CompareProduct(Product product1, Product product2) {
		super();
		this.product1 = product1;
		this.product2 = product2;
	}

	public CompareProduct() {
		super();
	}

	public Product getProduct1() {
		return product1;
	}

	public void setProduct1(Product product1) {
		this.product1 = product1;
	}

	public Product getProduct2() {
		return product2;
	}

	public void setProduct2(Product product2) {
		this.product2 = product2;
	}

	@Override
	public String toString() {
		return "[product1=" + product1 + ", product2=" + product2 + "]";
	}
	
	
}
