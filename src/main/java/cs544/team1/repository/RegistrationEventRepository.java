package cs544.team1.repository;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public interface RegistrationEventRepository extends JpaRepository<RegistrationEvent,Integer> {

//    @Query("select rv from RegistrationEvent rv join rv.registrationGroups rg " +
//            "where rg.students.studentId = :id")

    @Query("select distinct rv from RegistrationEvent rv join fetch rv.registrationGroups rg  join rg.students s " +
            "where s.studentId = :id")
    public RegistrationEvent getLatestRegistationEvents(@Param("id") String id);

//    @Query("select rv from RegistrationEvent rv join rv.registrationGroups rg " +
//            "join rg.academicBlocks ab join ab.courseOfferings co " +
//            "join rg.students s where s.studentId = :studentid")

//    @Query(value = "select s.id,s.studentId, e.*, g.*,ab.*,co.*\n" +
//            "            from Student s \n" +
//            "            inner join RegistrationGroup g \n" +
//            "            on s.group_ID = g.id\n" +
//            "            inner join RegistrationEvent e\n" +
//            "            on e.id = g.Reg_Event_ID\n" +
//            "\t\t\tinner join AcademicBlock ab\n" +
//            "            on ab.group_ID =g.id \n" +
//            "            inner join CourseOffering co\n" +
//            "            on co.blockID = ab.id \n" +
//            "            group by s.id = ?1" , nativeQuery = true)

//    @Query("select re from s join s.registrations rg  join  re " +
//            "join re.AcademicBlock ab join ab.CourseOffering where s.studentId = :id")

    //@Query("select rv from RegistrationEvent rv join rv.registrationGroups rg where rg.students.s = :id")


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
