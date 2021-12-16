package cs544.team1.utils;

import lombok.Getter;
import lombok.Setter;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;


@Getter
@Setter
public class EmailUtil {
    public static void sendEmailFromAPI(String receiver, String subject, String body)
            throws AddressException, MessagingException, IOException {
        Email email= new Email(receiver,subject,body);
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email.getSenderUserName(), email.getPassword());
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(email.getSenderUserName(), false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getReceiver()));
        msg.setSubject(email.getSubject());
        msg.setContent(email.getMailBody(), "text/html");
        msg.setSentDate(email.getSentDate());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(email.getMailBody(), "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }

}
