package cs544.team1.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Data
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Faculty extends Person {
	private String facultyId;
	private String title;
	@Embedded
	private Audit audit;
}
