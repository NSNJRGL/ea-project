package cs544.team1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs544.team1.model.RegistrationRequest;
import cs544.team1.model.Student;
import cs544.team1.service.IRegistrationRequestService;
import cs544.team1.service.IStudentService;

@RestController
@RequestMapping("/registration")
public class RegistrationRequestController {
	@Autowired
	IRegistrationRequestService registrationReqService;
	
	@Autowired
	IStudentService studentService;
	
	@GetMapping("/request/{id}")
	public ResponseEntity<List<RegistrationRequest>> get(@PathVariable Integer id) {
		try {
			List<RegistrationRequest> registrationRequests = new ArrayList<>();
//			Student student = (Student) studentService.findById(id).orElse(new Student());
	        List<RegistrationRequest> requests = registrationReqService.findByStudentId(id);
	        return new ResponseEntity(requests, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<List<RegistrationRequest>>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@PutMapping("/update")
	public void updateRegistrationRequest () {
		
	}
}
