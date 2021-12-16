package cs544.team1.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@NoArgsConstructor
@Data
@Getter
@Setter
@AllArgsConstructor
public class RegistrationEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "reg_event_id")
    private List<RegistrationGroup> registrationGroups = new ArrayList<>();

    @Embedded
    private Audit audit;
}
