package cs544.team1;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;


public class MailBean {

    @Scheduled(cron="0/5 * * * * *")
    public void cronMethod() {
        System.out.println("Cron expression"); }



}
