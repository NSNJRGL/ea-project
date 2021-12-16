package cs544.team1;

import com.github.javafaker.Faker;
import cs544.team1.model.AcademicBlock;
import cs544.team1.model.Semester;
import cs544.team1.service.IAcademicBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.time.LocalDate;

@SpringBootApplication
public class Application {
	@Autowired
	IAcademicBlockService academicBlockService;

	public static void main(String[] args) {
		System.out.println("Some changes");
		SpringApplication.run(Application.class, args);
	}


}

