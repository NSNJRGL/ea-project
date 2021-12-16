package cs544.team1.auth.modelRequests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class EmailRequest {
    private String receiver;
    private String subject;
    private String body;

}
