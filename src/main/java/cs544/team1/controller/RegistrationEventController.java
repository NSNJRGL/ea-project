package cs544.team1.controller;

import cs544.team1.model.*;
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

    @Autowired
    private IRegistrationEventService service;

    @Autowired
    private CourseOfferingSericeImpl courseOfferingSerice;

    @Autowired
    private RegistrationServiceImpl registrationService;

    @Autowired
    private RegistrationRequestServiceImpl registrationRequestService;

    @Autowired
    private RegistrationGroupServiceImpl registrationGroupService;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private AcademincBlockSericeImpl blockService;

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
            int sumOfCapacity = 0;
            for (AcademicBlock block : blocks) {
                CourseOffering courseOffering = courseOfferingSerice.findByBlocks(block);
                sumOfCapacity += courseOffering.getCapacity();
            }

            System.out.println("groups -------- " + sumOfCapacity);

            int capacity = 0;
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
                                capacity++;
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


    //#################################################################################################

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
    public ResponseEntity<RegistrationEvent> getOneTicket(@PathVariable int id) {
        Optional<RegistrationEvent> event = service.findById(id);

        if (event.isPresent()) {
            return ResponseEntity.ok(event.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
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
    public ResponseEntity<Object> deleteEvent(@PathVariable int id) {
        service.deleteById(id);
        System.out.println("Successfuly deleted Event");
        return ResponseEntity.noContent().build();

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
// Student to get the latest Registration events

    @GetMapping("/latest")
    public Object findLatestEvent() {

        RegistrationEvent latest = service.findFirstEvent();
        HashMap<String, String> notOpened = new HashMap<>();
        notOpened.put("Status", "Not Opened");
        HashMap<String, String> open_In_Progress = new HashMap<>();
        open_In_Progress.put("Status", "Opened and In Progress");
        HashMap<String, String> closed = new HashMap<>();
        closed.put("Status", "Closed");

        List<Object> objectss = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(latest.getStartDate())) {

            objectss.add(service.findFirstEvent());
            objectss.add(notOpened);

            return objectss;
        } else if (now.isBefore(latest.getEndDate()) && now.isAfter(latest.getStartDate())) {

            objectss.add(service.findFirstEvent());
            objectss.add(open_In_Progress);

            return objectss;
        } else {
            objectss.add(service.findFirstEvent());
            objectss.add(closed);

            return objectss;
        }
//        return service.findFirstEvent();
    }


}
