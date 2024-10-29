package com.mdcorporation.serviceBookingSystem.services.client;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdcorporation.serviceBookingSystem.dto.AdDetailForClientDto;
import com.mdcorporation.serviceBookingSystem.dto.AdDto;
import com.mdcorporation.serviceBookingSystem.dto.ReservationDto;
import com.mdcorporation.serviceBookingSystem.dto.ReviewDto;
import com.mdcorporation.serviceBookingSystem.entity.Ad;
import com.mdcorporation.serviceBookingSystem.entity.Reservation;
import com.mdcorporation.serviceBookingSystem.entity.Review;
import com.mdcorporation.serviceBookingSystem.entity.User;
import com.mdcorporation.serviceBookingSystem.enums.ReservationStatus;
import com.mdcorporation.serviceBookingSystem.enums.ReviewStatus;
import com.mdcorporation.serviceBookingSystem.repositry.AdRepositery;
import com.mdcorporation.serviceBookingSystem.repositry.ReservationRepositery;
import com.mdcorporation.serviceBookingSystem.repositry.ReviewRepositery;
import com.mdcorporation.serviceBookingSystem.repositry.UserRepositery;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private UserRepositery userRepositery;
	
	@Autowired
	private AdRepositery adRepositery;
	
	@Autowired
	private ReservationRepositery reservationRepositery;
	
	@Autowired
	private ReviewRepositery reviewRepositery;
	
	public List<AdDto> getAllClientAds() {
		return adRepositery.findAll().stream().map(Ad::getAdDto).collect(Collectors.toList());
		
	}
	
	public List<AdDto> searchAdByServiceName(String name) {
		return adRepositery.findByServiceNameContainingIgnoreCase(name).stream().map(Ad::getAdDto).collect(Collectors.toList());
		
	}
	

	public boolean bookService(ReservationDto reservationDto) {

		
		Optional<Ad> optionalAd = adRepositery.findById(reservationDto.getAd_id());
		Optional<User> optionalUser = userRepositery.findById(reservationDto.getUser_id());
	

		if (optionalAd.isPresent() && optionalUser.isPresent()) {
			Reservation res = new Reservation();
			res.setUser(optionalUser.get());
			res.setBookDate(reservationDto.getBookDate());
			res.setReservationStatus(ReservationStatus.PENDING);
			res.setAd(optionalAd.get());
			res.setCompany(optionalAd.get().getUser());
			res.setReviewStatus(ReviewStatus.FALSE);
			reservationRepositery.save(res);
			return true;
			
		}
		return false;

	}
	
	
	public AdDetailForClientDto getAdDtoForClient(Long adId) {
		Optional<Ad> optionalAd = adRepositery.findById(adId);
		AdDetailForClientDto adDetailForClientDto  = new AdDetailForClientDto();
		
		if (optionalAd.isPresent()) {
			adDetailForClientDto.setAdDto(optionalAd.get().getAdDto());
			List<Review> reviewList = reviewRepositery.findAllByAdId(adId);
			adDetailForClientDto.setReviewDtos(reviewList.stream().map(Review::getDto).collect(Collectors.toList()));
		}
		
		return adDetailForClientDto;
		}
	
	public List<ReservationDto> getAllBookingByUserId (Long userId){
		return reservationRepositery.findByUserId(userId).stream().map(Reservation :: reservationDto).collect(Collectors.toList());
		
	}
	
	//giving review to the service as a user @mohnish
	
	public boolean giveReview(ReviewDto reviewDto) {
		
		Optional<User> optionalUser = userRepositery.findById(reviewDto.getUserId());
		Optional<Reservation> optionalReservation = reservationRepositery.findById(reviewDto.getId());
		
		if (optionalUser.isPresent() && optionalReservation.isPresent()) {
			Review review = new Review();
			review.setDate(new Date());
			review.setRating(reviewDto.getRating());
			review.setReview(reviewDto.getReview());
			review.setAd(optionalReservation.get().getAd());
			review.setUser(optionalUser.get());
			reviewRepositery.save(review);
			
			Reservation booking  = optionalReservation.get();
			booking.setReviewStatus(ReviewStatus.TRUE);
			reservationRepositery.save(booking);
			return true;
			
		}
		
		
		return false;
		

	}
	

}
