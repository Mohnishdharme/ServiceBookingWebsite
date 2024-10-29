package com.mdcorporation.serviceBookingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile; // If you are handling file uploads

import com.mdcorporation.serviceBookingSystem.dto.AdDto;
import com.mdcorporation.serviceBookingSystem.dto.ReservationDto;
import com.mdcorporation.serviceBookingSystem.services.company.CompanyService;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("ad/{user_id}")
    public ResponseEntity<?> postAd(@PathVariable("user_id") Long userId, @RequestBody AdDto adDTO) throws IOException {
        
    	try {
            boolean success = companyService.postAd(userId, adDTO);
            
            if (success) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
		} catch (Exception e) {
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
    }
    
    @GetMapping("ads/{user_id}")
    public ResponseEntity<?> getAllAdd (@PathVariable ("user_id") Long userId){
    	return ResponseEntity.ok(companyService.getAllAdd(userId));
    }
    
    @GetMapping("ad/{ad_id}")
    public ResponseEntity<?> getAdd(@PathVariable ("ad_id") Long adId) {
    	AdDto adDto= companyService.getAdd(adId);
        if (adDto != null) {
        	return ResponseEntity.ok(adDto);
		}
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    @PutMapping("ad/{ad_id}")
    public ResponseEntity<?> updateAd(@PathVariable ("ad_id") Long adId, @RequestBody AdDto adDto) {
    	System.out.println(adId);
		boolean succes = companyService.updateAd(adId, adDto);
		if (succes) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
    
    @DeleteMapping("ad/{ad_id}")
    public ResponseEntity<?> delteAd(@PathVariable ("ad_id") Long adId){
    	boolean succes = companyService.deleteAd(adId);
    	if (succes) {
			return ResponseEntity.status(HttpStatus.OK).build();			
		}
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    @GetMapping("ad/bookings/{comany_id}")
    public ResponseEntity<?> getAllBookings(@PathVariable (value = "comany_id")Long companyId) {
    	List<ReservationDto> allbookings = companyService.getAllBookings(companyId);
    	
        return ResponseEntity.ok(allbookings);
    }
    
    @PostMapping("ad/bookingStatus/{booking_id}/{status}")
    public ResponseEntity<?> changeBookingStatus(@PathVariable (value="booking_id") Long booking_id ,@PathVariable (value="status")String status) {
        	boolean successes = companyService.changeBookingStatus(booking_id, status);
       
        	if (successes) {
        		return  ResponseEntity.status(HttpStatus.OK).build();
			}
        	
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    
    
}
