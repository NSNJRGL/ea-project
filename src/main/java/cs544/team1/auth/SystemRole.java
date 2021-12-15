package cs544.team1.auth;

import cs544.team1.model.Admin;
import cs544.team1.model.Faculty;
import cs544.team1.model.Person;
import cs544.team1.model.Student;

public class SystemRole {
   public static String getRole(Person p){
       String role="";
       if (p instanceof Student) {
           role="Student";
           System.out.println("I  amd student ");
       } else if (p instanceof Faculty) {
           role="Faculty";
           System.out.println("I am faculty ");
       } else if (p instanceof Admin) {
           System.out.println("I am adming");
           role="Admin";

       } else {
           System.out.println("I am no body ");

       }
       return role;
   }
}
