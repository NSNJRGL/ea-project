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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String code;
    private int capacity;

    @OneToOne
    private Course course;

    @OneToOne
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "block_id")
    private AcademicBlock block;

    @ToString.Exclude
    @OneToMany(mappedBy = "student")
	private List<Registration> registrations = new ArrayList<>();

    @Embedded
	private Audit audit;
}
