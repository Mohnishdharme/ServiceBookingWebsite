package com.mdcorporation.serviceBookingSystem.services.company;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdcorporation.serviceBookingSystem.dto.AdDto;
import com.mdcorporation.serviceBookingSystem.dto.ReservationDto;
import com.mdcorporation.serviceBookingSystem.entity.Ad;
import com.mdcorporation.serviceBookingSystem.entity.Reservation;
import com.mdcorporation.serviceBookingSystem.entity.User;
import com.mdcorporation.serviceBookingSystem.enums.ReservationStatus;
import com.mdcorporation.serviceBookingSystem.repositry.AdRepositery;
import com.mdcorporation.serviceBookingSystem.repositry.ReservationRepositery;
import com.mdcorporation.serviceBookingSystem.repositry.UserRepositery;

import io.jsonwebtoken.lang.Objects;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	private UserRepositery userRepositery;
	
	@Autowired
	private AdRepositery adRepositery;
	
	@Autowired
	private ReservationRepositery reservationRepositery;
	
	public boolean postAd(Long user_id, AdDto adDto) throws IOException {
		Optional<User> optionalUser = userRepositery.findById(user_id);
		if (optionalUser.isPresent()) {
			Ad ad = new Ad();
			ad.setServiceName(adDto.getServiceName());
			ad.setDescription(adDto.getDescription());
			ad.setImg(adDto.getImg());
			ad.setPrice(adDto.getPrice());
			ad.setUser(optionalUser.get());
			adRepositery.save(ad);
			return true;
		}
		return false;
	}
	
	public List<AdDto> getAllAdd(Long userId){
		return adRepositery.findAllByUserId(userId).stream().map(Ad::getAdDto).collect(Collectors.toList());
	}
	
	public AdDto getAdd(Long adId) {
		Optional<Ad> optionalAd = adRepositery.findById(adId);
		if ( optionalAd.isPresent()) {
			return optionalAd.get().getAdDto();
		}
		return null;
	}
	
	public boolean updateAd(Long adId , AdDto adDto) {
		Optional<Ad> optionalAd = adRepositery.findById(adId);
		if (optionalAd.isPresent()) {
			Ad ad = optionalAd.get();
			System.out.println(adDto.getPrice());
			ad.setServiceName(adDto.getServiceName());
			ad.setDescription(adDto.getDescription());
			ad.setImg(adDto.getImg());
			ad.setPrice(adDto.getPrice());
			ad.setId(adId);
			
			adRepositery.save(ad);
			
			return true;
		}
		return false;
	}
	
	public boolean deleteAd(Long adId) {
		Optional<Ad> optionalAd = adRepositery.findById(adId);
		if (optionalAd.isPresent()) {
			adRepositery.delete(optionalAd.get());
			return true;
		}
		return false;
	}
	
	public List<ReservationDto> getAllBookings(Long adId) {
		return reservationRepositery.findByCompanyId(adId).stream().map(Reservation::reservationDto).collect(Collectors.toList());
	}
	
	public boolean changeBookingStatus (Long id, String status) {
		Optional<Reservation> reservation = reservationRepositery.findById(id);
		
		if (reservation.isPresent()) {
			Reservation existingReservation = reservation.get();
		try {
			if (status.equals("APPROVED")) {
				
				existingReservation.setReservationStatus(ReservationStatus.APPROVED);
			}
			else {
				existingReservation.setReservationStatus(ReservationStatus.REJECTED);
			}
			reservationRepositery.save(existingReservation);
			
			return true;
		} catch (Exception e) {
			return false;
		}
		}
		return false;
	}

}
