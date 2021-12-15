package cs544.team1.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
@ManyToOne
@JoinColumn(name = "resource")
    private Resource resource;
    private String role;
    private boolean canGET;
    private boolean canPUT;
    private boolean canPOST;
    private boolean canDELETE;

}

