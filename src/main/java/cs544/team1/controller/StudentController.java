package cs544.team1.controller;


import cs544.team1.model.Registration;
import cs544.team1.model.Student;
import cs544.team1.service.IStudentService;
import cs544.team1.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @GetMapping("/")
    public List<Student> findAll( ){
        return studentService.findAll();
    }


 
    //#########################################################################################################
    // //Student to find registrations use case number 6  (4-in controller)

    @GetMapping("/registrations/{studentId}")
    public List<Registration> findRegistrationByStudent(@PathVariable String id){
        return studentService.findRegistrationByStudent(id);
    }
 

}
