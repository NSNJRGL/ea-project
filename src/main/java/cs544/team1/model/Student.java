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
public class Student extends Person {
    private String studentId;
    @OneToMany(mappedBy = "student")
    private List<Registration> registrations = new ArrayList<>();

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "group_id")
	private  RegistrationGroup group;

    @OneToMany
    @JoinColumn(name = "student_id")
    private List<RegistrationRequest> registrationsRequests = new ArrayList<>();
    @Embedded
    private Audit audit;
}
