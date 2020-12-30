package com.prgr.main.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//productid, name, category, sellername, rating ,description ,quantity,price.
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PRODUCT_ID")
	private int productId;
	
	@Column(name = "PRODUCT_NAME")
	@NotNull
	@Size(min=2,message="Product name cannot be empty")
	private String productName;
	
	@Column(name = "PRODUCT_CATEGORY")
	@NotNull
	@Size(min=2,message="Category cannot be empty")
	private String category;
	
	@Column(name = "SELLER_NAME")
	@NotNull
	@Size(min=2,message="Seller cannot be empty")
	private String sellerName;
	
	@Column(name = "PRODUCT_DESCRIPTION")
	@NotNull
	@Size(min=2,message="Description cannot be empty")
	private String description;
	
	@Column(name = "PRODUCT_PRICE")
	@NotNull
	private Long price;


	public Product() {

	}

	public Product(int productId, String productName, String category,
			String sellerName, String description, Long price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.sellerName = sellerName;
		this.description = description;
		this.price = price;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product Details: productId=" + productId + ", productName="
				+ productName + ", category=" + category + ", sellerName="
				+ sellerName + ", description=" + description + ", price="
				+ price+"\n";
	}

}
