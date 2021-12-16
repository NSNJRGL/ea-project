package cs544.team1.service;

import cs544.team1.model.RegistrationEvent;
import cs544.team1.model.RegistrationGroup;
import cs544.team1.repository.RegistrationGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationGroupServiceImpl implements IRegistrationGroupService  {
    @Autowired
    RegistrationGroupRepository repository;

    public List<RegistrationGroup> findByRegistrationEvent(long registrationEvent) {
        return repository.findByRegistrationId(registrationEvent);
    }

    @Override
    public List findAll() {
        return repository.findAll();
    }

    @Override
    public Object save(Object o) {
        return repository.save((RegistrationGroup) o);
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
}
