package cs544.team1.controller;

import cs544.team1.model.RegistrationEvent;
import cs544.team1.service.RegistrationEventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RegEvent")
public class RegistrationEventController {

    @Autowired
    private RegistrationEventServiceImpl registrationEventService;

    @GetMapping("/{id}")
    public @ResponseBody RegistrationEvent getRegEvent(@PathVariable String id){
        return registrationEventService.getLatestRegistationEvents(id);
    }
}
