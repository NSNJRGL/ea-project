package cs544.team1.service;

import cs544.team1.model.AcademicBlock;
import cs544.team1.model.Audit;
import cs544.team1.model.RegistrationGroup;
import cs544.team1.model.Semester;
import cs544.team1.repository.AcadamicBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AcademincBlockSericeImpl implements  IAcademicBlockService{

    @Autowired
    AcadamicBlockRepository repository;

    public List<AcademicBlock> findByRegistrationGroup(List<RegistrationGroup> registrationGroups) {
        return repository.findByRegistrationGroupIn(registrationGroups);
    }

    @Override
    public Object save(Object o) {
        return repository.save((AcademicBlock) o);
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
    public void deleteById(int id){

    }

    @Override
    public List<AcademicBlock> findAll() {
        return repository.findAll();
    }

}
