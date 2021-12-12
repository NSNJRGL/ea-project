package cs544.team1.model;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Embeddable
public class Audit {
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	private Person createdBy;
	private Person modifiedBy;
	
	public Audit(Person createdBy, Person modifiedBy) {
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}
	
	public Audit(Person modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
