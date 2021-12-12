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
	@ManyToOne
	@JoinColumn(name="registration_group_id")
	private RegistrationGroup registrationGroup;
	@OneToMany(mappedBy = "student")
	private List<Registration> registrations = new ArrayList<>();
	@Embedded
	private Audit audit;
}
