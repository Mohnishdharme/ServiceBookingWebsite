package com.mdcorporation.serviceBookingSystem.entity;

import java.util.Arrays;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.mdcorporation.serviceBookingSystem.dto.AdDto;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ads")
public class Ad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String serviceName;
	
	private String Description;
	
	private double price;
	
	private String img;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getServiceName() {
		return serviceName;
	}


	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	
	
	
	@Override
	public String toString() {
		return "Ad [id=" + id + ", serviceName=" + serviceName + ", Description=" + Description + ", price=" + price
				+ ", img=" + img + ", user=" + user + "]";
	}


	public AdDto getAdDto() {
		AdDto adDto = new AdDto();
		adDto.setId(id);
		adDto.setServiceName(serviceName);
		adDto.setDescription(Description);
		adDto.setPrice(price);
		adDto.setCompanyName(user.getName());
		adDto.setImg(img);
		return adDto;
	}
	

}
