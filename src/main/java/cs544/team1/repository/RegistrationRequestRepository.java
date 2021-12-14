package cs544.team1.repository;

import cs544.team1.model.RegistrationRequest;
import cs544.team1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest,Integer> {
	@Query(value = "SELECT * FROM registrationrequest WHERE student_id = ?1", nativeQuery = true)
	List<RegistrationRequest> findByStudentId(long studentId);
}
