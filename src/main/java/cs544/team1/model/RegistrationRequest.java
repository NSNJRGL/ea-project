package cs544.team1.model;

import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@DynamicUpdate
public class RegistrationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int priority;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Embedded
	private Audit audit;
    @ManyToOne
    private CourseOffering courseOffering;
    @ManyToOne
    private Student student;
    
    public boolean isPending() {
    	return status == Status.PENDING;
    }
}