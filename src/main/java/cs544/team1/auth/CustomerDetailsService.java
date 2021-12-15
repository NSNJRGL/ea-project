package cs544.team1.auth;

import cs544.team1.model.Admin;
import cs544.team1.model.Faculty;
import cs544.team1.model.Person;
import cs544.team1.model.Student;
import cs544.team1.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    IPersonService personService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person p = personService.loginByUserName(username);
        String role = null;
        System.out.println(p);
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("PPPP==" + p.getPassword());
        if (p instanceof Student) {
            role = "Student";
            System.out.println("I  amd student ");
        } else if (p instanceof Faculty) {
            role = "Faculty";
            System.out.println("I am faculty ");
        } else if (p instanceof Admin) {
            System.out.println("I am adming");
            role = "Admin";

        } else {
            System.out.println("I am no body ");

        }
        if (username == null && role == null) {
            throw new UsernameNotFoundException("Customer not found with username: " + username);
        }

        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                username,
                p.getPassword(),
                true,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                Arrays.asList(new SimpleGrantedAuthority(role)));

        return userDetails;
    }


}