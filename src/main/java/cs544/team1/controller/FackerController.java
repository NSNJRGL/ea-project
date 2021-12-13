package cs544.team1.controller;

import com.github.javafaker.Faker;
import cs544.team1.model.AcademicBlock;
import cs544.team1.model.Semester;
import cs544.team1.service.IAcademicBlockService;
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
     IAcademicBlockService service;

   // @GetMapping
    public List<AcademicBlock> findAll(){
        return service.findAll();
    }

    @GetMapping
    public   void myFaker(){
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

            service.save(academicBlock);


        }


    }
}
