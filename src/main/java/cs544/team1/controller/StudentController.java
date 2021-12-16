package cs544.team1.controller;


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


//    @PostMapping("/")
//    public Student save(@RequestBody Student student ){
//        return studentService.save(student);
//    }
//



}
