package cs544.team1.service;

import java.util.List;
import java.util.Optional;

import cs544.team1.model.RegistrationRequest;
import cs544.team1.model.Student;
import cs544.team1.projection.RegistrationRequestProjection;

public  interface IRegistrationRequestService extends GeneralService {
	
	public List<RegistrationRequestProjection> findByStudentId(long studentId);
//	public List<RegistrationRequest> findByStatus(String status);
}
