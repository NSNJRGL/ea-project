package cs544.team1.model;

import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Registration {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "course_offering_id")
    private CourseOffering courseOffering;
    private RegistrationRequest regRequest;
    @Embedded
	private Audit audit;
}
