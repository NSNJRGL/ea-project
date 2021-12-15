package cs544.team1.projection;

public interface RegistrationRequestProjection {
	int getId();
	int getPriority();
	String getStatus();
	
	CourseOfferingProjection getCourseOffering();
	StudentProjection getStudent();
	
	interface CourseOfferingProjection {
		int getId();
		String getCode();
	}
	
	interface StudentProjection {
		int getId();
	}
}
