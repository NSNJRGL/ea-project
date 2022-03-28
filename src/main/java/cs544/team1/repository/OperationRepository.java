package cs544.team1.repository;

import cs544.team1.model.Operation;
import cs544.team1.model.RegistrationRequest;
import cs544.team1.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface OperationRepository extends JpaRepository<Operation,Integer> {

//    @Query(value = "SELECT * FROM registrationrequest WHERE student_id = ?1", nativeQuery = true)
//    List<RegistrationRequest> findByStudentId(long studentId);
//
    @Query(value = "select  op.canGET  from Operation op inner join Resource re on " +
            "   op.resource.id = re.id where op.role=:role  and re.path=:path")
    boolean canGET( String path, String role);


    @Query(value = "select  op.canPUT  from Operation op inner join Resource re on " +
            "   op.resource.id = re.id where op.role=:role  and re.path=:path")
    boolean canPUT(String path, String role);


    @Query(value = "select  op.canPOST  from Operation op inner join Resource re on " +
            "   op.resource.id = re.id where op.role=:role  and re.path=:path")
    boolean canPOST( String path, String role);


    @Query(value = "select  op.canDELETE  from Operation op inner join Resource re on " +
            "   op.resource.id = re.id where op.role=:role  and re.path=:path")
    boolean canDELETE( String path, String role);
//
}
