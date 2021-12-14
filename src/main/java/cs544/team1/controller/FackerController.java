package cs544.team1.controller;

import com.github.javafaker.Faker;
import cs544.team1.model.*;
import cs544.team1.service.*;
import cs544.team1.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@RestController
@RequestMapping("/faker")
public class FackerController {

    @Autowired
     IAcademicBlockService academicBlockService;

    @Autowired
    IStudentService studentService;

    @Autowired
    IFacultyService facultyService;

    @Autowired
    IAdminService adminService;

    @Autowired
    IRegistrationEventService registrationEventService;

    @Autowired
    IRegistrationGroupService registrationGroupService;
    @Autowired
    ICourseService courseService;

    @GetMapping
    public void generateFakerData(){
        fakerAcademicBlock();
        fakerStudent();
        fakerFaculty();
        fakerAdmin();

        fakerRegistrationGroup();
        fakerRegistrationEvent();
        fakerCourse();


    }
    public void fakerRegistrationGroup() {
        String [] entry={"FEB","MAY","AUG","NOV"};
        for (int i = 0; i <3 ; i++) {
            RegistrationGroup obj = new RegistrationGroup();
            obj.setCode(entry[i]+LocalDate.now().getYear());
            registrationGroupService.save(obj);
         }
     }

    public void fakerCourse(){
        String[] names={
                "Fundamnetal of Progarmming Practice",
                "Modern Programming Practice",
                "Enterprise Archtecture",
                "Software Archttecture",
                "Algorithms",
                "Modern Web Application",
             };
        String[] codes={
                "CS390",
                "CS400",
                "CS544",
                "CS577",
                "CS425",
                "CS572",
        };
        for (int i = 0; i <6 ; i++) {
            Course course= new Course();
            course.setCourseName(names[i]);
            course.setCourseCode(codes[i]);
            course.setDescription(" description goes here");
            courseService.save(course);
        }
     }

public void fakerRegistrationEvent(){
        List<RegistrationGroup> groups= registrationGroupService.findAll();
    Faker faker= new Faker();
    for (int i = 1; i < 5; i++) {
        RegistrationEvent obj = new RegistrationEvent();
   obj.setStartDate(LocalDateTime.now());
   obj.setEndDate(LocalDateTime.now().plusDays(10));
   obj.setRegistrationGroups(groups);
   registrationEventService.save(obj);
    }
    for (int i = 1; i < 5; i++) {
        RegistrationEvent obj = new RegistrationEvent();
        obj.setStartDate(LocalDateTime.now().plusDays(10));
        obj.setEndDate(LocalDateTime.now().plusDays(15));
        obj.setRegistrationGroups(groups);
        registrationEventService.save(obj);
    }

}
    public  void fakerAdmin(){
        Faker faker= new Faker();
        for (int i = 1; i < 5; i++) {
            Admin obj = new Admin();
            String fname=faker.name().firstName();
            String lname=faker.name().lastName();
            obj.setFirstName(fname);
            obj.setLastName(lname);
            obj.setUsername(fname.toLowerCase().substring(1,1)+lname.toLowerCase());
            obj.setPassword(PasswordUtil.decode(fname));


            obj.setPosition("Registrar Manager");
            obj.setAdminId("ADM-21"+i);
            obj.setEmail(faker.bothify("????##@gmail.com"));
            adminService.save(obj);
        }
    }
    public  void fakerStudent(){
         Faker faker= new Faker();
         for (int i = 100; i < 600; i++) {

            Student student = new Student();
            String fname=faker.name().firstName();
            String lname=faker.name().lastName();
            student.setFirstName(fname);
            student.setLastName(lname);
             student.setUsername(fname.toLowerCase().subSequence(1,1)+lname.toLowerCase());
             student.setPassword(PasswordUtil.decode(fname));


            student.setStudentId("61-21"+i);
            AcademicBlock academicBlock= new AcademicBlock();
            academicBlock.setId(4 % i);
            RegistrationGroup group= new RegistrationGroup();
             group.setId(3);

             //student.setGroup(group);
            student.setEmail(faker.bothify("????##@gmail.com"));
            studentService.save(student);
           }
    }
    public  void fakerFaculty(){

        Faker faker= new Faker();

        for (int i = 10; i < 15; i++) {
            Faculty faculty = new Faculty();
            String fname=faker.name().firstName();
            String lname=faker.name().lastName();
            faculty.setFirstName(fname);
            faculty.setLastName(lname);
            faculty.setUsername(fname.toLowerCase().subSequence(1,1)+lname.toLowerCase());
            faculty.setPassword(PasswordUtil.decode(fname));


            faculty.setFacultyId("21"+i);
            faculty.setTitle("Professor");
            faculty.setEmail(faker.bothify("????##@gmail.com"));
            facultyService.save(faculty);
        }
    }
    public   void fakerAcademicBlock (){
        String[] blocks={
                "SEP","OCT","NOV","DEC","JAN","FEB","MAR","APR","MAY","JUN","JuL"
        };
        Faker faker= new Faker();
        for (int i = 0; i < 10; i++) {
            String code = faker.regexify("AB-"+i);
            String name = faker.expression("EXP-");
            AcademicBlock academicBlock= new AcademicBlock();
            academicBlock.setCode(blocks[i]);
            academicBlock.setName("ACadamic Block "+blocks[i]);
            academicBlock.setEndDate(LocalDate.now());
            academicBlock.setStartDate(LocalDate.now());
            academicBlock.setSemester(Semester.SPRING);

            academicBlockService.save(academicBlock);


        }

    }
}
