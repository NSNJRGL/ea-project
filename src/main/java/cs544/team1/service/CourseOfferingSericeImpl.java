package cs544.team1.service;

import cs544.team1.model.AcademicBlock;
import cs544.team1.model.Audit;
import cs544.team1.model.CourseOffering;
import cs544.team1.model.Semester;
import cs544.team1.repository.CourseOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseOfferingSericeImpl implements  ICourseOfferingService{

    @Autowired
    CourseOfferingRepository repository;
    @Override
    public Object save(Object o) {
        return repository.save((CourseOffering) o);
    }

    @Override
    public Optional findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<AcademicBlock> findAll() {
        List<AcademicBlock> list= new ArrayList<>();
        Audit a= new Audit();
        list.add(new AcademicBlock(1,"codd1","name1", LocalDate.now(),
                LocalDate.now(), Semester.SPRING,a));

        return list;

    }

}
