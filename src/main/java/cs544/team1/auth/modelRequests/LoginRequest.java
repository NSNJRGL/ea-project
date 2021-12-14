package com.miu.excercise15springdatajpa.model.modelRequests;

import lombok.*;

@Data @Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	
	private String username;
	private String password;
	

}
