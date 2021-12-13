package cs544.team1.repository;

import cs544.team1.model.Course;
import cs544.team1.model.RegistrationEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RegistrationEventRepository extends JpaRepository<RegistrationEvent,Integer> {

}
