package cs544.team1.model;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	@Embedded
	private Audit audit;
}
