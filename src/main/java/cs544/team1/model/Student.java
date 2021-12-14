package cs544.team1.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Data
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Student extends Person {
	private String studentId;
	@OneToMany
	@JoinColumn(name="student_id")
	private List<Registration> registrations = new ArrayList<>();


	@OneToMany
	@JoinColumn(name="student_id")
	private List<RegistrationRequest> registrationsRequests = new ArrayList<>();
	@Embedded
	private Audit audit;
}
