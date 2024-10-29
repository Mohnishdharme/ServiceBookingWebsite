package com.mdcorporation.serviceBookingSystem.entity;




import java.sql.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.mdcorporation.serviceBookingSystem.dto.ReservationDto;
import com.mdcorporation.serviceBookingSystem.enums.ReservationStatus;
import com.mdcorporation.serviceBookingSystem.enums.ReviewStatus;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "reservation", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"user_id", "ad_id"})
	})
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private ReservationStatus reservationStatus;
	
	private ReviewStatus reviewStatus;
	
	private Date bookDate;
	
	@ManyToOne(fetch = FetchType.LAZY , optional = false)
	@JoinColumn(name="user_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY , optional = false)
	@JoinColumn(name="company_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User company;
	
	@ManyToOne(fetch = FetchType.LAZY , optional = false)
	@JoinColumn(name="ad_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Ad ad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(ReviewStatus reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getCompany() {
		return company;
	}

	public void setCompany(User company) {
		this.company = company;
	}


	
	

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date date) {
		this.bookDate = date;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", reservationStatus=" + reservationStatus + ", reviewStatus=" + reviewStatus
				+ ", bookDate=" + bookDate + ", user=" + user + ", company=" + company + ", ad=" + ad + "]";
	}
	
	
	public ReservationDto reservationDto () {
		ReservationDto resDto = new ReservationDto();
		
		resDto.setId(id);
		resDto.setServiceName(ad.getServiceName());
		resDto.setBookDate(bookDate);
		resDto.setReservationStatus(reservationStatus);
		resDto.setReviewStatus(reviewStatus);
		
		resDto.setAd_id(ad.getId());
		resDto.setCompany_id(company.getId());
		resDto.setUser_name(user.getName());
		
		
		return resDto;
	}


}
