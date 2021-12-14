package cs544.team1.service;

import java.util.List;
import java.util.Optional;

import cs544.team1.model.RegistrationRequest;
import cs544.team1.model.Student;

public  interface IRegistrationRequestService extends GeneralService {
	public List<RegistrationRequest> findByStudentId(long studentId);
}
