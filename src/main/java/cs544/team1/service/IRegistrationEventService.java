package cs544.team1.service;


import cs544.team1.model.RegistrationEvent;
import cs544.team1.model.RegistrationRequest;
import cs544.team1.model.Student;
import cs544.team1.projection.RegistrationEventDTO;

import java.util.List;
import java.util.Optional;

public  interface IRegistrationEventService extends GeneralService {

    public RegistrationEvent updateEvent(RegistrationEvent event, int id);

    // ##################################################################################################
    // Student to see the latest Registration Event Use case Number 1 (2) - in service Interface

    public RegistrationEventDTO findFirstEvent();

}
