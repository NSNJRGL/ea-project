package cs544.team1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@EqualsAndHashCode
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Entity
public class RegistrationGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String code;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "registrationGroup")
    private List<AcademicBlock> academicBlocks;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "reg_group")
//    @JoinColumn(name = "reg_group_id")
    private List<Student> students = new ArrayList<>();

    @Embedded
    private Audit audit;

    public void addStudent(Student student) {
        students.add(student);
    }
}