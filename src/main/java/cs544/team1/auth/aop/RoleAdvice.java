package cs544.team1.auth.aop;

import cs544.team1.service.MailService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.mail.MessagingException;
import java.io.IOException;


@Aspect
@Component
public class RoleAdvice {

    @Autowired
    MailService mailService;

    @After("execution(* cs544.team1.controller.RegistrationRequestController.updateRegistrationRequest(..))")
    public void notifyUpdate(JoinPoint joinpoint) throws MessagingException, IOException {
        String receiver = "";
        String subject = "";
        String body = "";
        mailService.sendEmailAPI(receiver, subject, body);

    }


}
