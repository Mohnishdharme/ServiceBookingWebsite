package com.mdcorporation.serviceBookingSystem.dto;





import java.sql.Date;

import com.mdcorporation.serviceBookingSystem.enums.ReservationStatus;
import com.mdcorporation.serviceBookingSystem.enums.ReviewStatus;

public class ReservationDto {
	
	private Long id;
	
	private Date bookDate;
	
	private String serviceName;
	
	private ReservationStatus reservationStatus;
	
	private ReviewStatus reviewStatus;
	
	private Long user_id;
	
	private String user_name;
	
	private Long company_id;
	
	private Long ad_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate2) {
		this.bookDate = bookDate2;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	
	

	public Long getAd_id() {
		return ad_id;
	}

	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}

	@Override
	public String toString() {
		return "ReservationDto [id=" + id + ", bookDate=" + bookDate + ", serviceName=" + serviceName
				+ ", reservationStatus=" + reservationStatus + ", reviewStatus=" + reviewStatus + ", user_id=" + user_id
				+ ", user_name=" + user_name + ", company_id=" + company_id + ", ad_id=" + ad_id + "]";
	}
	
	

}