package cs544.team1.repository;

import cs544.team1.model.RegistrationRequest;
import cs544.team1.projection.RegistrationRequestProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest,Integer> {
	List<RegistrationRequestProjection> findByStudentId(long studentId);
	List<RegistrationRequest> findByStatus(String status);
}
