package cs544.team1.service;

import cs544.team1.model.AcademicBlock;
import cs544.team1.model.Audit;
import cs544.team1.model.Semester;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseOfferingSericeImpl implements  ICourseOfferingService{

    @Override
    public Object save(Object o) {
        return null;
    }

    @Override
    public Optional findById(int id) {
        return Optional.empty();
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
