package cs544.team1.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Address {
	@Column(nullable = false)
	private String street;
	@Column(nullable = false)
	private String postalCode;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String province;
	@Column(nullable = false)
	private String country;
	@Embedded
	private Audit audit;
}
