package cs544.team1.repository;

import cs544.team1.model.Registration;
import cs544.team1.model.RegistrationEvent;
import cs544.team1.model.RegistrationGroup;
import cs544.team1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student,Integer> {

//    @Query(value =
//            "SELECT rs.students_id FROM registrationgroup_student rs " +
//                    "where rs.RegistrationGroup_id in " +
//                    "(SELECT rg.id FROM registrationgroup rg where rg.reg_event_id = :registrationEventId)",
//            nativeQuery = true)
//    @Query("SELECT s from Student s join s.groups group where group.reg")
    @Query("select s from RegistrationEvent rv join rv.registrationGroups rg  join rg.students s " +
            "where rv.id= :registrationEventId")
//    @Query("SELECT s from Student s join RegistrationGroup rg join rg.students rs where  ")
    //public List<Student> getStudentsByGroupID(@Param("id") long id);
    //public List<Student> findStudentsByGroupId(long id);
    List<Student> findByRegistrationGroup(long registrationEventId);


    //##################################################################################################
    //Student to find registrations use case number 6  (1) - in repository

    @Query("select s.registrations from Student s where s.studentId = : id")
    List<Registration> findRegistrationByStudent(@Param("id") String id);


}
