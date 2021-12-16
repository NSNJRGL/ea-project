package cs544.team1.controller;


import cs544.team1.utils.EmailUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

@RestController
@RequestMapping("/api/mail")

public class EmailContoller {


    @PostMapping(value = "/send")
    public String sendEmailAPI(@RequestHeader("reciever") String reciever,
                               @RequestHeader("subject") String subject,
                               @RequestHeader("body") String body) throws AddressException, MessagingException, IOException {
        EmailUtil.sendEmailFromAPI(reciever,subject,body);
        return "Email sent successfully via Header";
    }



}
