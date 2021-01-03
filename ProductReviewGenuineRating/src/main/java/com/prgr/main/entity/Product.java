package com.prgr.main.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "PRODUCT")
/**
 * Products Entity class
 * @author Akshata
 *
 */
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private int productId;

	@Column(name = "PRODUCT_NAME")
	@NotNull
	@Size(min = 2, message = "Product name cannot be empty")
	private String productName;

	@Column(name = "PRODUCT_CATEGORY")
	@NotNull
	@Size(min = 2, message = "Category cannot be empty")
	private String category;

	@Column(name = "SELLER_NAME")
	@NotNull
	@Size(min = 2, message = "Seller cannot be empty")
	private String sellerName;

	@Column(name = "PRODUCT_DESCRIPTION")
	@NotNull
	@Size(min = 2, message = "Description cannot be empty")
	private String description;

	@Column(name = "PRODUCT_PRICE")
	@NotNull
	private Long price;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Review> review;

	public Product() {

	}

	public Product(int productId, @NotNull @Size(min = 2, message = "Product name cannot be empty") String productName,
			@NotNull @Size(min = 2, message = "Category cannot be empty") String category,
			@NotNull @Size(min = 2, message = "Seller cannot be empty") String sellerName,
			@NotNull @Size(min = 2, message = "Description cannot be empty") String description, @NotNull Long price,
			List<Review> review) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.sellerName = sellerName;
		this.description = description;
		this.price = price;
		this.review = review;
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

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", category=" + category
				+ ", sellerName=" + sellerName + ", description=" + description + ", price=" + price + ", review="
				+ review + "]";
	}

	
}
