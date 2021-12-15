package cs544.team1.auth;


import cs544.team1.auth.jwtUtils.JwtTokenProvider;
import cs544.team1.auth.modelRequests.LoginRequest;
import cs544.team1.auth.modelResponses.JwtResponse;
import cs544.team1.model.Person;
import cs544.team1.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
//@CrossOrigin
@RequestMapping("/api/")
public class JWTController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private IPersonService personService;
	
	@GetMapping("/ok")
	public String testing(){
		return "Am Ok";
	}
	

	@PostMapping("login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {

		 final ResponseEntity<String> response = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		System.out.println(authenticationRequest.getUsername());
		System.out.println(authenticationRequest.getPassword());
		System.out.println("_<_<_<_<_<_<_<_<_<_<_<_<___<_<");

		Person person= personService.loginByUserName(authenticationRequest.getUsername());
		System.out.println("---------------------");
		System.out.println(person.toString());

		System.out.println("-----------------------");
		System.out.println();
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
//		System.out.println("============");
		System.out.println(SHAHash.getSHA256(authenticationRequest.getPassword()));
		if (userDetails.getPassword().equals(SHAHash.getSHA256(authenticationRequest.getPassword()))){
String role=SystemRole.getRole(person);
			final String token = jwtTokenProvider.generateTokenWithRole(userDetails,role);
			System.out.println("token=="+token);
			return ResponseEntity.ok(new JwtResponse(token));
		}else
		{
			return ResponseEntity.status(401).body("Un Authorised");
		}

	}



	private ResponseEntity<String> authenticate(String username, String password) throws Exception {
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {
			return new ResponseEntity<>("Please confirm your registration",HttpStatus.BAD_REQUEST);

		} catch (BadCredentialsException e) {
			return new ResponseEntity<>("Bad credantials",HttpStatus.BAD_REQUEST);
		} 
		return null;
	}
	


	
	private ResponseEntity<String> error(String message) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
	}
	


}
