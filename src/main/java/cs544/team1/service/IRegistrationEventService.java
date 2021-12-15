package cs544.team1.service;


import cs544.team1.model.RegistrationEvent;
import cs544.team1.model.RegistrationRequest;
import cs544.team1.model.Student;

import java.util.List;
import java.util.Optional;

public  interface IRegistrationEventService extends GeneralService {

    public RegistrationEvent updateEvent(RegistrationEvent event, int id);

    public RegistrationEvent findFirstEvent();

}
