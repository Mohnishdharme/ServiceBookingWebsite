package com.mdcorporation.serviceBookingSystem.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdcorporation.serviceBookingSystem.entity.Review;

@Repository
public interface ReviewRepositery extends JpaRepository<Review, Long>{
	
	List<Review> findAllByAdId(Long adId);
	

}
