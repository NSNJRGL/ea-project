package cs544.team1.controller;


import cs544.team1.model.Registration;
import cs544.team1.model.Student;
import cs544.team1.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthTestController {

@GetMapping
  public String getTest(){
     return  "Get Teest";
  }

    @PutMapping
    public String putTest(){
        return  "Editng is Allowed ";
    }

    @PatchMapping
    public String patchTest(){
        return   "Patch is Allowed";
    }

    @PostMapping
    public String postTest(){
        return   "Post is Allowed";
    }



    @DeleteMapping
    public String deleteTest(){
        return   "Delete is Allowed";
    }





}
