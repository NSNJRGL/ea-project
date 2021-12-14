package cs544.team1.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Data
@Getter
@Setter
@AllArgsConstructor
public class RegistrationEvent {
	@Id
	@GeneratedValue
	private long id;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	@ManyToMany(mappedBy ="registrationEvents")
	private List<RegistrationGroup> registrationGroups;



	@Embedded
	private Audit audit;
}
