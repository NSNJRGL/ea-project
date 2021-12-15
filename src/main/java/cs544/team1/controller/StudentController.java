package cs544.team1.controller;


import cs544.team1.model.Student;
import cs544.team1.service.IStudentService;
import cs544.team1.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;
//    @GetMapping("/group/{id}")
//    public List<Student> getByGroupID(@PathVariable long id){
//        return studentService.getStudentsByGroupID(id);
//        //return  studentService.f
//    }
    @GetMapping("/all")
    public List<Student> getAll( ){
        return studentService.findAll();
    }
}
