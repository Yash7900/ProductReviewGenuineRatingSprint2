package com.prgr.main.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "REVIEW")
/**
 * Review Entity Class
 * @author YASH
 *
 */
public class Review implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "REVIEW_ID")
	private int reviewId;
	@Column(name = "USER_ID")
	private int userId;
	@Column(name = "REVIEW_RATE")
	@Min(value = 1, message = "Rating should not be less than 0")
    @Max(value = 5, message = "Rating should not be greater than 5")
	private int rate;
	@Column(name = "REVIEW_DESCRIPTION")
	@Size(min =2, max =50, message = "Description Me must be between 2 and 50 characters")
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(foreignKey = @ForeignKey(name = "productId"), name = "productId")
	@JsonIgnore
	private Product product;
	
	public Review()
	{}

	public Review(int reviewId, int userId,
			@Min(value = 1, message = "Rating should not be less than 0") @Max(value = 5, message = "Rating should not be greater than 5") int rate,
			@Size(min = 10, max = 200, message = "Description Me must be between 10 and 200 characters") String description,
			Product product) {
		super();
		this.reviewId = reviewId;
		this.userId = userId;
		this.rate = rate;
		this.description = description;
		this.product = product;
	}



	public Review(
			@Min(value = 1, message = "Rating should not be less than 0") @Max(value = 5, message = "Rating should not be greater than 5") int rate,
			@Size(min = 2, max = 50, message = "Description Me must be between 2 and 50 characters") String description) {
		super();
		this.rate = rate;
		this.description = description;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getDescrption() {
		return description;
	}

	public void setDescrption(String description) {
		this.description = description;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", userId=" + userId + ", rate=" + rate + ", descrption=" + description
				+ ", product=" + product + "]";
	}

		
}
