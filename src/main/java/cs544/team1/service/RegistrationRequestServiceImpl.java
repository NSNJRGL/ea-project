package cs544.team1.service;

import cs544.team1.model.RegistrationRequest;
import cs544.team1.model.Student;
import cs544.team1.repository.RegistrationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationRequestServiceImpl implements IRegistrationRequestService  {
    @Autowired
    RegistrationRequestRepository repository;

    @Override
    public List<RegistrationRequest> findAll() {
        return repository.findAll();
    }

    @Override
    public Object save(Object o) {
        return repository.save((RegistrationRequest) o);
    }

    @Override
    public Optional<RegistrationRequest> findById(int id) {
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
	public List<RegistrationRequest> findByStudentId(long studentId) {
		return repository.findByStudentId(studentId);
	}
    
}
