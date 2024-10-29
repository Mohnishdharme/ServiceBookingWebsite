package com.mdcorporation.serviceBookingSystem.services.company;

import java.io.IOException;
import java.util.List;



import com.mdcorporation.serviceBookingSystem.dto.AdDto;
import com.mdcorporation.serviceBookingSystem.dto.ReservationDto;


public interface CompanyService {
	
	boolean postAd(Long user_id, AdDto adDto) throws IOException;
	
	List<AdDto> getAllAdd(Long userId);
	
	AdDto getAdd(Long adId);
	
	boolean updateAd(Long adId , AdDto adDto);
	
	boolean deleteAd(Long adId);
	
	List<ReservationDto> getAllBookings(Long adId);
	
	boolean changeBookingStatus (Long id, String status);
	
	

	

}
