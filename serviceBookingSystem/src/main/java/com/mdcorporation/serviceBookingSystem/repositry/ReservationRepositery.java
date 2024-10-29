package com.mdcorporation.serviceBookingSystem.repositry;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdcorporation.serviceBookingSystem.entity.Ad;
import com.mdcorporation.serviceBookingSystem.entity.Reservation;
import com.mdcorporation.serviceBookingSystem.entity.User;


public interface ReservationRepositery extends JpaRepository<Reservation, Long> {
	
	List<Reservation> findByCompanyId(Long adId);
	
	List<Reservation> findByUserId(Long userId);
	

	
	List<Reservation> findByAdId(Long adId);

}
