package cs544.team1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs544.team1.model.RegistrationRequest;
import cs544.team1.service.IRegistrationRequestService;

@RestController
@RequestMapping("/registration-events")
public class RegistrationEventController {
	@Autowired
	IRegistrationRequestService registrationReqService;
	
	@PatchMapping("/{id}")
	public ResponseEntity<String> updateEvent (@PathVariable long eventId) {
		try {
			List<RegistrationRequest> registrationRequests = registrationReqService.findAll();
			
			
			
	        return new ResponseEntity(HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
