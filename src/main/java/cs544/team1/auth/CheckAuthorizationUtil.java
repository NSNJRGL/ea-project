package cs544.team1.auth;

import cs544.team1.repository.OperationRepository;
import cs544.team1.service.IOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckAuthorizationUtil {
    @Autowired
    OperationRepository repository;

    public  boolean check( String method, String path,String role){

        if (method.equalsIgnoreCase("GET")){

        return   repository.canGET(path,role);
        }else if(method.equalsIgnoreCase("POST")){
           return repository.canPOST(path,role);

        }else  if(method.equalsIgnoreCase("PUT")||method.equalsIgnoreCase("PATCH")){
           return repository.canPUT(path,role);
        }else  if(method.equalsIgnoreCase("DELETE")){
          return   repository.canDELETE(path,role);
        }else {
            return true;
        }
       // System.out.println("Returing ==="+value);

    // return value==1?true:false;
    }
}
