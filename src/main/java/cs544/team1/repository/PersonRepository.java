package cs544.team1.repository;

import cs544.team1.model.Course;
import cs544.team1.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person,Integer> {

//    @Query(value = "select p from  Person p where  p.username=: username  and p.password=:password",nativeQuery = true)
//     Person login(@Param("username")String username, @Param("password") String password);

    //@Query(value = "  from  Person  p  where  p.username =:username ",nativeQuery = true)
    Person loginWithUserName(@Param("username")String username);

}
