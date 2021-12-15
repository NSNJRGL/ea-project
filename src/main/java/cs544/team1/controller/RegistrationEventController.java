package cs544.team1.controller;

import cs544.team1.model.*;
import cs544.team1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
//            List<RegistrationGroup> regGroup = registrationGroupService.findByRegistrationEvent(id);
            List<Student> students = studentService.findByRegistrationGroup(id);
            System.out.println("student" + students);
            return new ResponseEntity(students, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
