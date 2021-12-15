package cs544.team1.repository;

import cs544.team1.model.Course;
import cs544.team1.model.RegistrationEvent;
import cs544.team1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RegistrationEventRepository extends JpaRepository<RegistrationEvent,Integer> {

//    @Query("select rv from RegistrationEvent rv join rv.registrationGroups rg " +
//            "where rg.students.studentId = :id")

    @Query("select rv from RegistrationEvent rv join rv.registrationGroups rg  join rg.students s " +
            "where s.studentId = :id")

//    @Query("select rv from RegistrationEvent rv join rv.registrationGroups rg " +
//            "join rg.academicBlocks ab join ab.courseOfferings co " +
//            "join rg.students s where s.studentId = :studentid")
    public List<RegistrationEvent> getLatestRegistationEvents(@Param("id") String id);



    RegistrationEvent findFirstByOrderByStartDateDesc();

}
