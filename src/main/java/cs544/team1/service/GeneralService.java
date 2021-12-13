package cs544.team1.service;

import java.util.List;
import java.util.Optional;


public interface GeneralService<T>  {

    List<T> findAll();
    T save(T t);
    Optional<T> findById(int id);
    Long count();
    void delete(int id);


}
