package cs544.team1.utils;

import cs544.team1.model.Person;

public class LoggedInUser {

    private String role;

    public static String getRole() {
        return LoggedInUser.getRole();
    }

    public static void setRole(String role) {
        LoggedInUser.setRole(role);
    }

    public static Person get(){
        return new Person();
    }

}
