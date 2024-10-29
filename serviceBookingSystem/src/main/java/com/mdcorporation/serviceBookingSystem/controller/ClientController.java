package com.mdcorporation.serviceBookingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdcorporation.serviceBookingSystem.dto.AdDetailForClientDto;
import com.mdcorporation.serviceBookingSystem.dto.AdDto;
import com.mdcorporation.serviceBookingSystem.dto.ReservationDto;
import com.mdcorporation.serviceBookingSystem.dto.ReviewDto;
import com.mdcorporation.serviceBookingSystem.services.client.ClientService;



@RestController
@RequestMapping("api/client")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@GetMapping("ads")
	public ResponseEntity<?> allAds() {
		try {
			List<AdDto> adDto = clientService.getAllClientAds();
			if (adDto != null) {
				return ResponseEntity.ok(adDto);
			}
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
		
	@GetMapping("ads/search/{service_name}")
	public ResponseEntity<?> searchAdByServiceName(@PathVariable ("service_name") String serviceName) {
		try {
			List<AdDto> adDto = clientService.searchAdByServiceName(serviceName);
			if (adDto != null) {
				return ResponseEntity.ok(adDto);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
	}
	
	@PostMapping("ad/bookService")
	public ResponseEntity<?> bookService(@RequestBody ReservationDto reservationDto) {
		try {
			boolean res= clientService.bookService(reservationDto);
			if (res) {
				return ResponseEntity.status(HttpStatus.OK).build();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	@GetMapping("ad/details/{ad_id}")
	public ResponseEntity<?> getAdDtoForClient(@PathVariable (value = "ad_id") Long adID) {
		try {
			AdDetailForClientDto succsess = clientService.getAdDtoForClient(adID);
			if (succsess != null) {
				return ResponseEntity.ok(succsess);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	@GetMapping("mybookings/{user_id}")
	public ResponseEntity<?> getAllBookingByUserId(@PathVariable (value = "user_id")Long userId) {
		return ResponseEntity.ok(clientService.getAllBookingByUserId(userId));
	}
	
	@PostMapping("review")
	public ResponseEntity<?> giveReview(@RequestBody ReviewDto reviewDto) {
		try {
			boolean success = clientService.giveReview(reviewDto);
			if (success) {
				return ResponseEntity.status(HttpStatus.OK).build();
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	
	
	
	
	

}
