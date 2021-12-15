package cs544.team1.service;


import cs544.team1.model.Person;

public  interface IPersonService extends GeneralService {
    Person loginByUserName(String username);

}
