package cs544.team1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@SecondaryTable(name = "description")
public class Course {
    @Id
    @GeneratedValue
    private long id;
    private String courseCode;
    private String courseName;
    // @Table(name = "description")
    private String description;
    @Embedded
	private Audit audit;
}