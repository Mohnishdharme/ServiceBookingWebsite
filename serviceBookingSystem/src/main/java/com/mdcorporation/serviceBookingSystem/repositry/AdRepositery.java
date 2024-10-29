package com.mdcorporation.serviceBookingSystem.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdcorporation.serviceBookingSystem.entity.Ad;

public interface AdRepositery extends JpaRepository<Ad, Long>{

	List<Ad> findAllByUserId(Long userId);
	
	List<Ad> findByServiceNameContainingIgnoreCase(String serviceName);


}
