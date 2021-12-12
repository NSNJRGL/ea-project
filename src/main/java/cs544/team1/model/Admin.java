package cs544.team1.model;

import javax.persistence.Column;
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
public class Admin extends Person {
	@Column(nullable = false)
	private String adminId;
	@Column(nullable = false)
	private String position;
	@Embedded
	private Audit audit;
}
