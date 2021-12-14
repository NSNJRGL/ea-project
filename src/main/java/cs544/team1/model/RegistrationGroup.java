package cs544.team1.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

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
    @GeneratedValue
    private long id;
    private String code;
    @OneToMany
    private List<AcademicBlock> academicBlocks = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "group_id")
    private List<Student> students= new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "registration_group_event",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = { @JoinColumn(name = "event_id")}

    )
    private List<RegistrationEvent>registrationEvents;
    @Embedded
    private Audit audit;
}