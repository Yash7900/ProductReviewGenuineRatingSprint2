package com.prgr.main.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REVIEW")
public class Review implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "REVIEW_ID")
	private int reviewId;
	@Column(name = "PRODUCT_ID")
	private int productId;
	@Column(name = "USER_ID")
	private int userId;
	@Column(name = "REVIEW_RATE")
	private int reviewRate;
	@Column(name = "REVIEW_DESCRPTION")
	private String reviewDescrption;
	
	public Review()
	{}
	public Review(int productId,int userId,int reviewRate,String reviewDescrption)
	{
		super();
		//this.reviewId = reviewId;
		this.productId = productId;
		this.userId=userId;
		this.reviewRate=reviewRate;
		this.reviewDescrption=reviewDescrption;
	}
	public int reviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int reviewRate() {
		return reviewRate;
	}
	public void setReviewRate(int reviewRate) {
		this.reviewRate= reviewRate;
	}
	public String reviewDescrption() {
		return reviewDescrption;
	}
	public void setReviewDescrption(String reviewDescrption) {
		this.reviewDescrption = reviewDescrption;
	}
	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", productId=" + productId
				+", userId=" + userId + ",reviewRate" + reviewRate + ",reviewDescription" +reviewDescrption+
				 "]";
	}
}
