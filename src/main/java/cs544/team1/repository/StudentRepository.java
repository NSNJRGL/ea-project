package cs544.team1.repository;

import cs544.team1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student,Integer> {

}
