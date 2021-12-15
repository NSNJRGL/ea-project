package cs544.team1.controller;

import com.github.javafaker.Faker;
import cs544.team1.model.*;
import cs544.team1.service.*;
import cs544.team1.auth.SHAHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

		fakerRegistrationGroup();
		fakerRegistrationEvent();
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
		List<RegistrationGroup> groups = registrationGroupService.findAll();
		Faker faker = new Faker();
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

	public void fakerAdmin() {
		Faker faker = new Faker();
		for (int i = 1; i < 5; i++) {
			Admin obj = new Admin();
			String fname = faker.name().firstName();
			String lname = faker.name().lastName();
			obj.setFirstName(fname);
			obj.setLastName(lname);
			obj.setUsername(fname);
			obj.setPassword(SHAHash.getSHA256(fname));

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
			String fname = faker.name().firstName().trim();
			String lname = faker.name().lastName().trim();
			student.setFirstName(fname);
			student.setLastName(lname);
			student.setUsername( fname);
			student.setPassword(SHAHash.getSHA256(fname));

			student.setStudentId("61-21" + i);
			AcademicBlock academicBlock = new AcademicBlock();
			academicBlock.setId(4 % i);
			RegistrationGroup group = new RegistrationGroup();
			group.setId(3);

			// student.setGroup(group);
			student.setEmail(faker.bothify("????##@gmail.com"));
			studentService.save(student);
		}
	}

	public void fakerFaculty() {

		Faker faker = new Faker();

		for (int i = 10; i < 15; i++) {
			Faculty faculty = new Faculty();
			String fname = faker.name().firstName().trim();
			String lname = faker.name().lastName().trim();
			faculty.setFirstName(fname);
			faculty.setLastName(lname);
			faculty.setUsername(fname);
			faculty.setPassword(SHAHash.getSHA256(fname));

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
			academicBlock.setCode(blocks[i]);
			academicBlock.setName("ACadamic Block " + blocks[i]);
			academicBlock.setEndDate(LocalDate.now());
			academicBlock.setStartDate(LocalDate.now());
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
		for (Course course : courses) {
			Random r = new Random();
			int facultyRandom = r.nextInt(faculties.size());
			int academicRandom = r.nextInt(academicBlocks.size());
			String facultyName = "" + faculties.get(facultyRandom).getFirstName().charAt(0)
					+ faculties.get(facultyRandom).getLastName().charAt(0);
			String courseCode = course.getCourseCode();
			CourseOffering courseOffering = new CourseOffering();
			courseOffering.setCode(courseCode + "-" + academicBlocks.get(academicRandom).getCode() + "-" + facultyName);
			courseOffering.setFaculty(faculties.get(facultyRandom));
			courseOffering.setCourse(course);
			courseOffering.setBlock(academicBlocks.get(academicRandom));
			courseOffering.setCapacity(40);
			List<RegistrationRequest> registReq = new ArrayList<>();
			i++;

			for (Student student : students) {
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
}
