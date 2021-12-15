package cs544.team1.service;

import cs544.team1.model.Operation;
import cs544.team1.model.Resource;
import cs544.team1.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationServiceImpl implements IOperationService  {
    @Autowired
    OperationRepository repository;

    @Override
    public List findAll() {
        return repository.findAll();
    }

    @Override
    public Object save(Object o) {
        return repository.save((Operation) o);
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

    }


}
