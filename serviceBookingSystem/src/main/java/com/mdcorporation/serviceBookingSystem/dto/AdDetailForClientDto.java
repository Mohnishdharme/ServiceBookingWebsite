package com.mdcorporation.serviceBookingSystem.dto;

import java.util.List;

public class AdDetailForClientDto {

	private AdDto adDto;
	
	List<ReviewDto> reviewDtos;

	public AdDto getAdDto() {
		return adDto;
	}

	public void setAdDto(AdDto adDto) {
		this.adDto = adDto;
	}

	public List<ReviewDto> getReviewDtos() {
		return reviewDtos;
	}

	public void setReviewDtos(List<ReviewDto> reviewDtos) {
		this.reviewDtos = reviewDtos;
	}

	@Override
	public String toString() {
		return "AdDetailForClientDto [adDto=" + adDto + ", reviewDtos=" + reviewDtos + "]";
	}
 
	
	
	
}
