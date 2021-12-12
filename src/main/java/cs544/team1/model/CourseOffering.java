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
    private int availableSeats;
    @OneToOne
    private Faculty faculty;
    @OneToOne
    private AcademicBlock block;
    
    @OneToMany(mappedBy = "courseOffering")
	private List<Registration> registrations = new ArrayList<>();
    @Embedded
	private Audit audit;
}
