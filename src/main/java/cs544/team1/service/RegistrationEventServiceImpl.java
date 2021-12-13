package cs544.team1.service;

import cs544.team1.model.RegistrationEvent;
import cs544.team1.model.RegistrationRequest;
import cs544.team1.repository.RegistrationEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationEventServiceImpl implements IRegistrationEventService  {
    @Autowired
    RegistrationEventRepository repository;

//    @Override
//    public Optional<RegistrationEvent> getLatestRegistationEvents(Integer id, RegistrationRequest registrationRequest){
//        Optional<RegistrationEvent> registrationEvent = repository.findById(id);
//        if(registrationEvent.isPresent()) {
//            //return registrationEvent.get();
//        } else {
//            throw new RecordNotFoundException("No employee record exist for given id");
//        }
//    }

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
