package cs544.team1.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicBlock{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private Semester semester;

    @OneToMany(mappedBy = "block")
    private List<CourseOffering> courseOfferings = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "reg_group_id")
    private RegistrationGroup registrationGroup;

    @Embedded
	private Audit audit;
}