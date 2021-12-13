package cs544.team1.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import cs544.team1.utils.LoggedInUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Embeddable

public class Audit {

//    @Column(name = "created_at")
//
//    private LocalDateTime createdOn;

//    @OneToOne()
//    @Column(name="created_by",updatable = false, insertable = false)
    //private Person createdBy;

//    @Column(name = "updated_at")

//    private LocalDateTime updatedOn;

//    @OneToOne
//    private Person updatedBy;

//    @PrePersist
//    public void prePersist() {

        //createdOn = LocalDateTime.now();

     //   createdBy = LoggedInUser.get();

//    }

//    @PreUpdate
//
//    public void preUpdate() {
//
//        updatedOn = LocalDateTime.now();
//
//        updatedBy = LoggedInUser.get();
//
//    }
}
