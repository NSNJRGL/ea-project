package cs544.team1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public  MailBean getMailBean(){
        return new MailBean();
    }
}
