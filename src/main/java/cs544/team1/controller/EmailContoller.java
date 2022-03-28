package cs544.team1.controller;


import cs544.team1.auth.modelRequests.EmailRequest;
import cs544.team1.utils.EmailUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

@RestController
@RequestMapping("/api/mail")

public class EmailContoller {


    @PostMapping(value = "/send")
    public String sendEmailAPI(@RequestBody EmailRequest req
                              ) throws AddressException, MessagingException, IOException {
        EmailUtil.sendEmailFromAPI(req.getReceiver(),req.getSubject(),req.getBody());
        return "Email sent successfully via Header";
    }



}
