package cs544.team1.service;

import cs544.team1.utils.EmailUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;


@Service
public class MailService {


    public String sendEmailAPI( String receiver,
                              String subject,
                               String body) throws AddressException, MessagingException, IOException {
        EmailUtil.sendEmailFromAPI(receiver,subject,body);
        return "Email sent successfully via Header";
    }

}
