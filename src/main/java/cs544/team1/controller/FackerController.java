package cs544.team1.controller;

import com.github.javafaker.Faker;
import cs544.team1.model.*;
import cs544.team1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
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
	IRegistrationRequestService registrationRequestService;

	@Autowired
	IRegistrationGroupService registrationGroupService;
	@Autowired
	ICourseService courseService;
	@Autowired
	ICourseOfferingService courseOfferingService;

	@GetMapping
	public void generateFakerData() {
		fakerAcademicBlock();
		fakerStudent();
		fakerFaculty();
		fakerAdmin();
		fakerRegistrationEvent();
		fakerRegistrationGroup();
		fakerCourse();
		fakerCourseOffering();
	}

	public void fakerRegistrationGroup() {
		String[] entry = { "FEB", "MAY", "AUG", "NOV" };
		for (int i = 0; i < 3; i++) {
			RegistrationGroup obj = new RegistrationGroup();
			obj.setCode(entry[i] + LocalDate.now().getYear());
			registrationGroupService.save(obj);
		}
	}

	public void fakerCourse() {
		String[] names = { "Fundamnetal of Progarmming Practice", "Modern Programming Practice",
				"Enterprise Archtecture", "Software Archttecture", "Algorithms", "Modern Web Application", };
		String[] codes = { "CS390", "CS400", "CS544", "CS577", "CS425", "CS572", };
		for (int i = 0; i < 6; i++) {
			Course course = new Course();
			course.setCourseName(names[i]);
			course.setCourseCode(codes[i]);
			course.setDescription(" description goes here");
			courseService.save(course);
		}
	}

	public void fakerRegistrationEvent() {
		Faker faker = new Faker();
		for (int i = 1; i < 5; i++) {
			RegistrationEvent obj = new RegistrationEvent();
			obj.setStartDate(LocalDateTime.now());
			obj.setEndDate(LocalDateTime.now().plusDays(10));
			registrationEventService.save(obj);
		}
		for (int i = 1; i < 5; i++) {
			RegistrationEvent obj = new RegistrationEvent();
			obj.setStartDate(LocalDateTime.now().plusDays(10));
			obj.setEndDate(LocalDateTime.now().plusDays(15));
			registrationEventService.save(obj);
		}

	}

	public void fakerAdmin() {
		Faker faker = new Faker();
		for (int i = 1; i < 5; i++) {
			Admin obj = new Admin();
			obj.setFirstName(faker.address().firstName());
			obj.setLastName(faker.address().lastName());
			obj.setPosition("Registrar Manager");
			obj.setAdminId("ADM-21" + i);
			obj.setEmail(faker.bothify("????##@gmail.com"));
			adminService.save(obj);
		}
	}

	public void fakerStudent() {
		Faker faker = new Faker();
		for (int i = 100; i < 200; i++) {
			Student student = new Student();
			student.setFirstName(faker.address().firstName());
			student.setLastName(faker.address().lastName());
			student.setStudentId("61-21" + i);
			AcademicBlock academicBlock = new AcademicBlock();
			academicBlock.setId(4 % i);
			student.setEmail(faker.bothify("????##@gmail.com"));
			studentService.save(student);
		}
	}

	public void fakerFaculty() {

		Faker faker = new Faker();

		for (int i = 10; i < 15; i++) {
			Faculty faculty = new Faculty();
			faculty.setFirstName(faker.address().firstName());
			faculty.setLastName(faker.address().lastName());
			faculty.setFacultyId("21" + i);
			faculty.setTitle("Professor");
			faculty.setEmail(faker.bothify("????##@gmail.com"));
			facultyService.save(faculty);
		}
	}

	public void fakerAcademicBlock() {
		String[] blocks = { "SEP", "OCT", "NOV", "DEC", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JuL" };
		Faker faker = new Faker();
		for (int i = 0; i < 10; i++) {
			String code = faker.regexify("AB-" + i);
			String name = faker.expression("EXP-");
			AcademicBlock academicBlock = new AcademicBlock();
			academicBlock.setCode("2021-12A-12D");
			academicBlock.setName("ACadamic Block " + blocks[i]);
			academicBlock.setEndDate(LocalDate.of(2021, 12, 28));
			academicBlock.setStartDate(LocalDate.of(2021, 12, 1));
			academicBlock.setSemester(Semester.SPRING);

			academicBlockService.save(academicBlock);
		}
	}
	
	public void fakerCourseOffering() {

		List<Course> courses = courseService.findAll();
		List<Faculty> faculties = facultyService.findAll();
		List<AcademicBlock> academicBlocks = academicBlockService.findAll();
		List<Student> students = studentService.findAll();

		int i = 0;
		for(Course course : courses) {
			Random r = new Random();
			int facultyRandom =  r.nextInt(faculties.size());
			int academicRandom =  r.nextInt(academicBlocks.size());
			String facultyName = "" + faculties.get(facultyRandom).getFirstName().charAt(0) + faculties.get(facultyRandom).getLastName().charAt(0);
			String courseCode = course.getCourseCode();
			CourseOffering courseOffering = new CourseOffering();
			courseOffering.setCode(courseCode + "-" + academicBlocks.get(academicRandom).getCode() + "-" + facultyName );
			courseOffering.setFaculty(faculties.get(facultyRandom));
			courseOffering.setCourse(course);
			courseOffering.setBlock(academicBlocks.get(academicRandom));
			courseOffering.setCapacity(40);
			List<RegistrationRequest> registReq = new ArrayList<>();
			i++;
			
			for(Student student : students) {
				RegistrationRequest req = new RegistrationRequest();
				req.setPriority(i);
				req.setStatus(Status.PENDING);
				
				registrationRequestService.save(req);
				
				registReq.add(req);
				student.addRegistrationReq(req);
			}
			
			courseOffering.setRegistrationsRequests(registReq);
			
			courseOfferingService.save(courseOffering);
		}
	}
	
//	public void fakerRegistrationRequest() {
//		List<Student> students = studentService.findAll();
//		List<CourseOffering> courseOfferings = courseOfferingService.findAll();
//		
//		for(Student student : students) {
//			Random r = new Random();
//			courseOfferings.get(r.nextInt(courseOfferings.size()));
//			
//			RegistrationRequest req = new RegistrationRequest();
//			req.setPriority(r.nextInt(courseOfferings.size()));
//			req.setStatus(Status.PENDING);
//			
//			registrationRequestService.save(req);
//		}
//	}
}
