package cs544.team1.auth.modelRequests;

import lombok.*;

@Data @Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	
	private String username;
	private String password;
	

}
