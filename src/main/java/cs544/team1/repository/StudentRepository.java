package cs544.team1.repository;

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
    @Query("select s from RegistrationEvent rv join rv.registrationGroups rg join rg.students s " +
            "where rv.id= :registrationEventId")
    List<Student> findByRegistrationEvent(long registrationEventId);
}
