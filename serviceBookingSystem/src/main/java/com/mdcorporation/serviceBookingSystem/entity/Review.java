package com.mdcorporation.serviceBookingSystem.entity;





import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.mdcorporation.serviceBookingSystem.dto.ReviewDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date date;
	
	private String review;
	
	private Long rating;
	
	@ManyToOne(fetch = FetchType.LAZY ,optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY ,optional = false)
	@JoinColumn(name = "ad_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Ad ad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date2) {
		this.date = date2;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", date=" + date + ", review=" + review + ", rating=" + rating + ", user=" + user
				+ ", ad=" + ad + "]";
	}
	
	
	public ReviewDto getDto() {
		ReviewDto review = new ReviewDto();
		
		review.setId(id);
		review.setAdId(ad.getId());
		review.setClientName(user.getName());
		review.setReview(getReview());
		review.setRating(rating);
		review.setDate(date);
		review.setUserId(user.getId());
		review.setServiceName(ad.getServiceName());
		
		return review;
		
	}
	

}
