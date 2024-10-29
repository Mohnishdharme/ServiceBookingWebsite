package com.mdcorporation.serviceBookingSystem.services.client;

import java.util.List;

import com.mdcorporation.serviceBookingSystem.dto.AdDetailForClientDto;
import com.mdcorporation.serviceBookingSystem.dto.AdDto;
import com.mdcorporation.serviceBookingSystem.dto.ReservationDto;
import com.mdcorporation.serviceBookingSystem.dto.ReviewDto;

public interface ClientService {

	
	List<AdDto> getAllClientAds();
	
	List<AdDto> searchAdByServiceName(String name);
	
	boolean bookService(ReservationDto reservationDto);
	
	AdDetailForClientDto getAdDtoForClient(Long adId);
	
	List<ReservationDto> getAllBookingByUserId (Long userId);
	
	boolean giveReview(ReviewDto reviewDto);

	}
