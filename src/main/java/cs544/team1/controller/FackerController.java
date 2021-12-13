package cs544.team1.controller;

import com.github.javafaker.Faker;
import cs544.team1.model.AcademicBlock;
import cs544.team1.model.Faculty;
import cs544.team1.model.Semester;
import cs544.team1.model.Student;
import cs544.team1.service.IAcademicBlockService;
import cs544.team1.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/faker")
public class FackerController {

    @Autowired
     IAcademicBlockService academicBlockService;

    @Autowired
    IStudentService studentService;

    @GetMapping
    public void generateFakerData(){
        fakerAcademicBlock();
        fakerStudent();
    }

    public List<AcademicBlock> findAll(){
        return academicBlockService.findAll();
    }


    public  void fakerStudent(){
        String[] blocks={
                "SEP","OCT","NOV","DEC","JAN","FEB","MAR","APR","MAY","JUN","JuL"
        };
        Faker faker= new Faker();

        for (int i = 10; i < 99; i++) {
            Student student = new Student();
            student.setFirstName(faker.address().firstName());
            student.setLastName(faker.address().lastName());
            student.setStudentId("61-21"+i);
            student.setEmail(faker.bothify("????##@gmail.com"));
            studentService.save(student);
           }
    }
    public  void fakerFaculty(){
        String[] blocks={
                "SEP","OCT","NOV","DEC","JAN","FEB","MAR","APR","MAY","JUN","JuL"
        };
        Faker faker= new Faker();

        for (int i = 10; i < 30; i++) {
            Faculty faculty = new Faculty();
            faculty.setFirstName(faker.address().firstName());
            faculty.setLastName(faker.address().lastName());

            faculty.setFacultyId("21"+i);
            faculty.setEmail(faker.bothify("????##@gmail.com"));
            studentService.save(faculty);
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
