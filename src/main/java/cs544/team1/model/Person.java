package cs544.team1.model;

import javax.persistence.*;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	@Embedded
	@AttributeOverrides( {
			@AttributeOverride(name="street", column=@Column(name="home_street")),
			@AttributeOverride(name="city", column=@Column(name="home_city")),
			@AttributeOverride(name="postalCode", column=@Column(name="home_postal_code")),
			@AttributeOverride(name="province", column=@Column(name="home_province")),
			@AttributeOverride(name="country", column=@Column(name="home_country"))
	})
	private Address homeAddress;
	@Embedded
	@AttributeOverrides( {
			@AttributeOverride(name="street", column=@Column(name="mailing_street")),
			@AttributeOverride(name="city", column=@Column(name="mailing_city")),
			@AttributeOverride(name="postalCode", column=@Column(name="mailing_postal_code")),
			@AttributeOverride(name="province", column=@Column(name="mailing_province")),
			@AttributeOverride(name="country", column=@Column(name="mailing_country"))
	})
	private Address mailingAddress;
	@Size(min = 10, max = 10)
	private String phoneNumber;
	@Email
	private String email;

	private String username;
	private String password;
}
