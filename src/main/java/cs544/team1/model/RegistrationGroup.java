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
public class RegistrationGroup {
    @Id
    @GeneratedValue
    private long id;
    private String code;
    @OneToMany
    private List<AcademicBlock> academicBlocks = new ArrayList<>();
    @OneToMany
    @JoinColumn(name="registration_event_id")
    private List<RegistrationEvent> registrationEvents = new ArrayList<>();
    @Embedded
    private Audit audit;
}