package cs544.team1.model;

import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class RegistrationRequest {
    @Id
    @GeneratedValue
    private long id;
    private Student student;
    private  CourseOffering courseOffering;
    private  boolean status;
    @Embedded
	private Audit audit;
}