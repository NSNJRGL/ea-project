package cs544.team1.service;

import cs544.team1.model.*;
import cs544.team1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements IStudentService  {
    @Autowired
    StudentRepository repository;

    public List<Student> findByRegistrationEvent(long registrationEventId) {
        return repository.findByRegistrationEvent(registrationEventId);
    }

    @Override
    public List findAll() {
        return repository.findAll();
    }

    @Override
    public Object save(Object o) {
        return repository.save((Student)o);
    }

    @Override
    public Optional findById(int id) {
        return repository.findById(id);
    }
//    @Override
//    public List<Student> getStudentsByGroupID(long id){
//        return repository.getStudentsByGroupID(id);
//        //return repository.findStudentsByGroupId(id);
//    }


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

    //##################################################################################################
    //Student to find registrations use case number 6  (3)- in service implementation
    @Override
    public List<Registration> findRegistrationByStudent(String id){
        return repository.findRegistrationByStudent(id);
    }

}
