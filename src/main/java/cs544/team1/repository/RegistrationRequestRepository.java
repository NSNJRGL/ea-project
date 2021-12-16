package cs544.team1.repository;

import cs544.team1.model.AcademicBlock;
import cs544.team1.model.CourseOffering;
import cs544.team1.model.RegistrationRequest;
import cs544.team1.model.Student;
import cs544.team1.projection.RegistrationRequestProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest,Integer> {

	List<RegistrationRequestProjection> findByStudentId(long studentId);
	List<RegistrationRequest> findByStatus(String status);
	@Query("from RegistrationRequest where priority = :priority and block = :block and student = :student")
	RegistrationRequest findByAttributes(int priority, AcademicBlock block, Student student);
}
