package cs544.team1.controller;

import cs544.team1.model.*;
import cs544.team1.projection.RegistrationEventDTO;
import cs544.team1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/registration-events")
public class RegistrationEventController {

    private IRegistrationEventService service;

    @Autowired
    private CourseOfferingSericeImpl courseOfferingSerice;

    @Autowired
    private RegistrationServiceImpl registrationService;

    @Autowired
    private RegistrationRequestServiceImpl registrationRequestService;

    @Autowired
    RegistrationServiceImpl regService;
    private StudentServiceImpl studentService;

    @Autowired
    private AcademincBlockSericeImpl blockService;

    @Autowired
    private RegistrationGroupServiceImpl registrationGroupService;

    @GetMapping("/")
    public void getPrintSomethin() {
        System.out.println("Controller Testing");
    }

//    @GetMapping("/{id}")
//    public @ResponseBody List<RegistrationEvent> getRegEvent(@PathVariable String id){
//        return registrationEventService.getLatestRegistationEvents(id);
//    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<String> updateRegistrationEvent(@PathVariable long id) {
        try {

//            List<RegistrationRequestProjection> requests = new ArrayList<>();
//            for (Student student : students) {
//                List<RegistrationRequestProjection> list = registrationRequestService.findByStudentId(student.getId());
//
//                for (RegistrationRequestProjection request : list) {
//                    requests.add(request);
//                }
//            }
//
//            for(RegistrationRequestProjection request : requests) {
//                System.out.println("student" + request.getStudent());
//            }

            List<RegistrationGroup> groups = registrationGroupService.findByRegistrationEvent(id);
            List<AcademicBlock> blocks = blockService.findByRegistrationGroup(groups);

            System.out.println("groups -------- " + groups.size() + " " + groups);
            System.out.println("block -------- " + blocks.size() + " " + blocks);
            for (AcademicBlock block : blocks) {
                int priority = 1;
                List<Student> students = studentService.findByRegistrationEvent(id);

                while (students.size() > 0) {
                    if (priority % 2 == 0) {
                        Collections.reverse(students);
                    }
                    for (Iterator<Student> iter = students.iterator(); iter.hasNext(); ) {
                        Student student = iter.next();
                        RegistrationRequest registrationRequest = registrationRequestService.findByAttributes(priority, block, student);
                        if (registrationRequest != null) {
                            CourseOffering courseOffering = registrationRequest.getCourseOffering();
                            if (courseOffering.getCapacity() > 0) {
                                Registration registration = new Registration();
                                registration.setStudent(student);
                                registration.setCourseOffering(courseOffering);
                                registrationService.save(registration);
                                courseOffering.setCapacity(courseOffering.getCapacity() - 1);
                                courseOfferingSerice.save(courseOffering);
                                iter.remove();
                            }
                            System.out.println("Registration ---- " + students.size() + students);
                        }
                    }
                    priority++;
                }
            }

            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Get all Registration Events ########################################################################
    @GetMapping
    public ResponseEntity<List<RegistrationEvent>> findAll() {
        List<RegistrationEvent> events = service.findAll();
        if (events.isEmpty()) {
            System.out.println("No result to show!");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(events);
    }

    // Get a Registration Event by ID ########################################################################
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationEvent> getOneEvent(@PathVariable int id) {
        Optional<RegistrationEvent> event = service.findById(id);

        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());

    }

    // Add a Registration Event ########################################################################
    @PostMapping("/addEvent")
    public void addEvent(@RequestBody RegistrationEvent registrationEvent) {
        System.out.println("In the Post Method");
        service.save(registrationEvent);
        System.out.println("Successfuly added Event");
    }


    // Delete a Registration Event ########################################################################
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable int id) {
        service.deleteById(id);
    }

    // Update a Registration Event ########################################################################
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnEvent(@PathVariable Integer id, @RequestBody RegistrationEvent eventt) {
        if (id.equals(eventt.getId())) {
            System.out.println("In the If Statement");
            return ResponseEntity.ok(service.updateEvent(eventt, id));
        } else {
            System.out.println("In the Else Statement");
            return ResponseEntity.badRequest().build();

        }
    }

    //########################################################################
    // ##################################################################################################
    // Student to see the latest Registration Event Use case Number 1 (4) - In Controller

    @GetMapping("/latest")
    public RegistrationEventDTO findLatestEvent() {
        return service.findFirstEvent();
    }
}
