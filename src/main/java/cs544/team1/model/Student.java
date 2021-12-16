package cs544.team1.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
//@NamedQuery(name = "Studnet.getByGroup",query = " select s from Student  s where s.group.id=:groupId")
public class Student extends Person {
	private String studentId;
	@OneToMany
	@JoinColumn(name="student_id")
	@JsonIgnore
	@Transient
	private List<Registration> registrations = new ArrayList<>();

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "group_ID")
	private  RegistrationGroup group;
	@OneToMany
	@JoinColumn(name="student_id")
	@JsonIgnore
	private List<RegistrationRequest> registrationsRequests = new ArrayList<>();
	@Embedded
	private Audit audit;
}
