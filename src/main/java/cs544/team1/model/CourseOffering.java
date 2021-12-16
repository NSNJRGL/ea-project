package cs544.team1.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class CourseOffering {
    @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String code;
    @OneToOne
    private Course course;
    private int capacity;
    @OneToOne
    private Faculty faculty;


    @ManyToOne
    @JoinColumn(name = "blockID")
    private AcademicBlock block;


    @OneToMany
    @JoinColumn(name="course_offering_id")
    @JsonIgnore
	private List<Registration> registrations = new ArrayList<>();

//    @OneToMany
//    @JoinColumn(name="course_offering_id")
//    private List<RegistrationRequest> registrationsRequests = new ArrayList<>();

    @Embedded
	private Audit audit;
}
