package cs544.team1.repository;

import cs544.team1.model.Course;
import cs544.team1.model.RegistrationGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RegistrationGroupRepository extends JpaRepository<RegistrationGroup,Integer> {
    @Query(value = "SELECT * FROM registrationgroup where reg_event_id = ?1", nativeQuery = true)
    List<RegistrationGroup> findByRegistrationId(long id);
}
