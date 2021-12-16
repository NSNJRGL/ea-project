package cs544.team1.service;


import cs544.team1.model.Registration;
import cs544.team1.model.Student;

import java.util.List;

public  interface IStudentService extends GeneralService {
    //public List<Student> getStudentsByGroupID(long id);
    //public List<Student> findStudentsByGroupId(long id);

    //##################################################################################################
    //Student to find registrations use case number 6  (2) - in service interface

    List<Registration> findRegistrationByStudent(String id);

}
