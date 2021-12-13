package cs544.team1.service;

import cs544.team1.model.RegistrationRequest;

import java.util.List;

public interface IStudentService {

    public List<RegistrationRequest> getLatestRegistationEvents(Integer id, RegistrationRequest registrationRequest);
}
