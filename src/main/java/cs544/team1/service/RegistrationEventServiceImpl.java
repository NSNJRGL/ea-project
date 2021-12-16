package cs544.team1.service;

import cs544.team1.model.RegistrationEvent;
import cs544.team1.projection.RegistrationEventDTO;
import cs544.team1.model.RegistrationRequest;
import cs544.team1.repository.RegistrationEventRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationEventServiceImpl implements IRegistrationEventService {

//    private SessionFactory sf;
//
//    public void setSessionFactory(SessionFactory sf) {
//        this.sf = sf;
//    }

    @Autowired
    RegistrationEventRepository repository;


    public List<RegistrationEvent> getLatestRegistationEvents(String studentID) {

        return repository.getLatestRegistationEvents(studentID);
    }

    public List<RegistrationEvent> getCurrentEvents(LocalDateTime now) {
        return repository.getCurrentEvents(now);
    }

    public RegistrationEvent findByRegistrationRequest(long id) {
        return repository.findByRegistrationRequest(id);
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
    public Optional<RegistrationEvent> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

//    @Override
//    public void deleteById(int id){
//
//    }

    @Override
    public void deleteById(int id) {
        RegistrationEvent managed = findById(id).orElse(null);
        if (managed != null) {
            repository.deleteById(id);
            System.out.println("Registration event deleted");
        } else {
            System.out.println("Registration event ID doesn't exist");
        }
    }


//    @Override
//    public RegistrationEvent updateEvent(RegistrationEvent event,int id) {
//        RegistrationEvent entity = (RegistrationEvent) findById(id).orElse(null);
//        assert entity != null;
//        entity.setStartDate(event.getStartDate());
//        entity.setEndDate(event.getEndDate());
//
//        return repository.save(entity);
//    }

    @Override
    public RegistrationEvent updateEvent(RegistrationEvent event, int id) {
        RegistrationEvent entity = findById(id).orElse(null);
        if (entity != null) {
            entity.setStartDate(event.getStartDate());
            entity.setEndDate(event.getEndDate());
//            entity.setRegistrationGroup(event.getRegistrationGroup());
            return repository.save(entity);
        }
        return null;

    }


//    @Override
//    public RegistrationEvent findFirstEvent(){
//        return repository.findFirstByOrderByStartDateDesc();
//    }


    // ##################################################################################################
    // Student to see the latest Registration Event Use case Number 1 (3) - in Service Implementation

    @Override
    public RegistrationEventDTO findFirstEvent() {

        RegistrationEvent latest = repository.findFirstByOrderByStartDateDesc();
        RegistrationEventDTO dto = new RegistrationEventDTO();
        dto.setAudit(latest.getAudit());
        dto.setEndDate(latest.getEndDate());
        dto.setStartDate(latest.getStartDate());
        dto.setId(latest.getId());


        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(latest.getStartDate())) {
            dto.setStatus("notOpened");
            return dto;
        } else if (now.isBefore(latest.getEndDate()) && now.isAfter(latest.getStartDate())) {
            dto.setStatus("open_In_Progress");
            return dto;
        } else {
            dto.setStatus("closed");
            return dto;
        }
    }


}
