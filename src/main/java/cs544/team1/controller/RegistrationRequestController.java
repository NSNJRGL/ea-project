package cs544.team1.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import cs544.team1.model.RegistrationEvent;
import cs544.team1.service.RegistrationEventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs544.team1.model.RegistrationRequest;
import cs544.team1.model.Status;
import cs544.team1.model.Student;
import cs544.team1.projection.RegistrationRequestProjection;
import cs544.team1.service.IRegistrationRequestService;
import cs544.team1.service.IStudentService;

@RestController
@RequestMapping("/registration")
public class RegistrationRequestController {
    @Autowired
    IRegistrationRequestService registrationReqService;

    @Autowired
    IStudentService studentService;

    @Autowired
    RegistrationEventServiceImpl registrationEventService;

    //	TODO: replace id with current user
    @GetMapping("/request/{id}")
    public ResponseEntity<List<RegistrationRequest>> get(@PathVariable Integer id) {
        try {
            List<RegistrationRequestProjection> requests = registrationReqService.findByStudentId(id);
            return new ResponseEntity(requests, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    //	TODO: replace id with current user
    @PutMapping("/update")
    public ResponseEntity<String> updateRegistrationRequest(@RequestBody List<RegistrationRequest> requests) {
        List<RegistrationEvent> events = registrationEventService.getCurrentEvents(LocalDateTime.now());

        if (events.isEmpty()) {
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            RegistrationEvent currentEvent = events.get(0);
            for (RegistrationRequest request : requests) {
                RegistrationEvent reEvent = registrationEventService.findByRegistrationRequest(request.getId());
                if (reEvent.getId() != currentEvent.getId()) {
                    return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
                }
                if (request.isPending()) {
                    request.setStatus(Status.SAVED);
                    registrationReqService.save(request);
                }
            }
            return new ResponseEntity(HttpStatus.OK);
        }

    }
}
