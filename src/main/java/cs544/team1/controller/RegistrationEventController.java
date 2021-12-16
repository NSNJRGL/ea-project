package cs544.team1.controller;

import cs544.team1.model.*;
import cs544.team1.projection.RegistrationRequestProjection;
import cs544.team1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/RegEvent")
public class RegistrationEventController {

    @Autowired
    private RegistrationEventServiceImpl registrationEventService;

    @Autowired
    private RegistrationGroupServiceImpl registrationGroupService;

    @Autowired
    private RegistrationRequestServiceImpl registrationRequestService;

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/")
    public void getPrintSomethin(){
        System.out.println("Controller Testing");
    }

    @GetMapping("/{id}")
    public @ResponseBody List<RegistrationEvent> getRegEvent(@PathVariable String id){
        return registrationEventService.getLatestRegistationEvents(id);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<String> updateRegistrationEvent (@PathVariable long id) {
        try {
            List<Student> students = studentService.findByRegistrationGroup(id);
            List<RegistrationRequestProjection> requests = new ArrayList<>();
            for (Student student : students) {
                List<RegistrationRequestProjection> list = registrationRequestService.findByStudentId(student.getId());

                for (RegistrationRequestProjection request : list) {
                    requests.add(request);
                }
            }

            for(RegistrationRequestProjection request : requests) {
                System.out.println("student" + request.getStudent());
            }

            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
