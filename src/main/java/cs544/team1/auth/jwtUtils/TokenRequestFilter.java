package cs544.team1.auth.jwtUtils;

import cs544.team1.auth.CheckAuthorizationUtil;
import cs544.team1.model.RegistrationRequest;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class TokenRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    CheckAuthorizationUtil checkAuthorizationUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String role = "";
        final String requestTokenHeader = request.getHeader("Authorization");
        System.out.println(requestTokenHeader);
        String username = null;
        String token = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

            token = requestTokenHeader.substring(7);
            try {
                username = tokenProvider.getUsernameFromToken(token);
                role = tokenProvider.getRoleFromToken(token);

            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            //userDetails

            // if token is valid configure Spring Security to manually set
            // authentication
            if (tokenProvider.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        System.out.println("URI=" + request.getMethod());
        System.out.println("URI" + request.getRequestURI());
        System.out.println("Protocoal" + request.getProtocol());
        System.out.println("AuthgType" + request.getAuthType());
        System.out.println("ROLE==" + role);
        String method = request.getMethod();
        String path = request.getRequestURI();
        if(path.contains("login")||path.contains("faker")){
            chain.doFilter(request, response);

        } else {
            System.out.println("Role is "+role);
            if (checkAuthorizationUtil.check(method, path, role)) {
                chain.doFilter(request, response);
            } else {
             // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                response.setStatus(401);
              //  response.get
            }

        }


    }

}
