package cs544.team1.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	@OneToMany(targetEntity=RegistrationGroup.class,cascade = CascadeType.ALL,
			fetch = FetchType.LAZY, orphanRemoval = true)
	//@JoinTable(name="RegEvent_RegGroup")
	@JoinColumn(name="Reg_ID")
	//@OrderColumn(name="sequence")
	private List<RegistrationGroup> registrationGroups = new ArrayList<>();

	@Embedded
	private Audit audit;
}
