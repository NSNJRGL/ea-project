package cs544.team1.service;

import cs544.team1.model.Admin;
import cs544.team1.model.Faculty;
import cs544.team1.model.Person;
import cs544.team1.model.Student;
import cs544.team1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements IPersonService {
    @Autowired
    PersonRepository repository;

    @Override
    public List findAll() {
        return repository.findAll();
    }

    @Override
    public Object save(Object o) {
        return repository.save((Person) o);
    }

    @Override
    public Optional findById(int id) {
        return
                repository.findById(id);
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public void delete(int id) {
//repository.delete(id);
    }

    @Override
    public void deleteById(int id){

    }

    @Override
    public Person loginByUserName(String username) {
        System.out.println("MEthod=" + this.getClass().getName() + " --Loginwith username");
        Person p = repository.loginWithUserName(username);
        System.out.println("++++++++++++++++++++++++++++++");


        System.out.println("SIMPLEName==" + p.getClass().getSimpleName());


        return p;
    }
}
