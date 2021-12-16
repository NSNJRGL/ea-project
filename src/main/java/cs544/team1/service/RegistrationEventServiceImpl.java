package cs544.team1.service;

import cs544.team1.model.RegistrationEvent;
import cs544.team1.repository.RegistrationEventRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationEventServiceImpl implements IRegistrationEventService  {

    private SessionFactory sf;

    public void setSessionFactory(SessionFactory sf) {
        this.sf = sf;
    }

    @Autowired
    RegistrationEventRepository repository;


    public RegistrationEvent getLatestRegistationEvents(String studentID) {

        return repository.getLatestRegistationEvents(studentID);
    }


    @Override
    public List findAll() {
        return repository.findAll();
    }

    @Override
    public Object save(Object o) {
        return repository.save((RegistrationEvent) o);
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
}
