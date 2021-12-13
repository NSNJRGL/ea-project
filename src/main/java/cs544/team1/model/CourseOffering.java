package cs544.team1.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class CourseOffering {
    @Id
    @GeneratedValue
    private long id;
    private String code;
    @OneToOne
    private Course course;
    private int capacity;
    @OneToOne
    private Faculty faculty;


    @ManyToOne
    @JoinColumn(name = "block_id")
    private AcademicBlock block;


    @OneToMany
    @JoinColumn(name="course_offering_id")
	private List<Registration> registrations = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="course_offering_id")
    private List<RegistrationRequest> registrationsRequests = new ArrayList<>();

    @Embedded
	private Audit audit;
}
