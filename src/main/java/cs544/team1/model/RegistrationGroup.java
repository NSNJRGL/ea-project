package cs544.team1.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@EqualsAndHashCode
@NoArgsConstructor
@Setter @Getter
@ToString
@AllArgsConstructor
public class RegistrationGroup {
    @Id
    @GeneratedValue
    private long id;
    private String code;
    @OneToMany
    private List<AcademicBlock> academicBlocks = new ArrayList<>();
    @OneToMany
    private RegistrationEvent registrationEvent;
    @OneToMany(mappedBy = "registrationGroup")
    private List<Student> students = new ArrayList<>();
    @Embedded
	private Audit audit;
}