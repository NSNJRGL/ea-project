package cs544.team1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Entity
public class RegistrationGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String code;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "group_ID")
    private List<AcademicBlock> academicBlocks;

    //@Transient
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    //@JoinColumn(name = "Reg_Group_ID")
    //@JsonManagedReference
    @JsonIgnore
    private List<Student> students = new ArrayList<>();


//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "registration_group_event",
//            joinColumns = {@JoinColumn(name = "group_id")},
//            inverseJoinColumns = { @JoinColumn(name = "event_id")}
//
//    )
 //   private List<RegistrationEvent>registrationEvents ;
    @Embedded
    private Audit audit;

    public void addAcademicBlock(AcademicBlock block) {
        academicBlocks.add(block);
    }

//    public void addStudent(Student student) {
//        students.add(student);
//    }
}