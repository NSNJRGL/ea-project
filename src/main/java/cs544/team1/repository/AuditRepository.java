package cs544.team1.repository;

import cs544.team1.model.AcademicBlock;
import cs544.team1.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AuditRepository extends JpaRepository<Audit,Integer> {

}
