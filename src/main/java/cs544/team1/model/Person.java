package cs544.team1.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Person {
	@Id
	@GeneratedValue
	private long id;
	private String firstName;
	private String lastName;
	@Embedded
	private Address homeAddress;
	@Embedded
	private Address mailingAddress;
	@Size(min = 10, max = 10)
	private String phoneNumber;
	@Email
	private String email;
}
