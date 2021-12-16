package cs544.team1.repository;

import cs544.team1.model.Course;
import cs544.team1.model.RegistrationEvent;
import cs544.team1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public interface RegistrationEventRepository extends JpaRepository<RegistrationEvent, Integer> {

//    @Query("select rv from RegistrationEvent rv join rv.registrationGroups rg " +
//            "where rg.students.studentId = :id")

    @Query("select rv from RegistrationEvent rv join rv.registrationGroups rg  join rg.students s " +
            "where s.studentId = :id")

//    @Query("select rv from RegistrationEvent rv join rv.registrationGroups rg " +
//            "join rg.academicBlocks ab join ab.courseOfferings co " +
//            "join rg.students s where s.studentId = :studentid")
    public List<RegistrationEvent> getLatestRegistationEvents(@Param("id") String id);

    @Query("from RegistrationEvent re where :currentDate BETWEEN re.startDate and re.endDate")
    List<RegistrationEvent> getCurrentEvents(LocalDateTime currentDate);

    @Query("from RegistrationEvent re join re.registrationGroups rg" +
            " join rg.students st join st.registrationsRequests rr" +
            " where rr.id = :id")
    RegistrationEvent findByRegistrationRequest(long id);

    // ##################################################################################################
    // Student to see the latest Registration Event Use case Number 1 (1) - n repository

    RegistrationEvent findFirstByOrderByStartDateDesc();

}
