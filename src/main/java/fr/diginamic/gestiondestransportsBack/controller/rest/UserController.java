package fr.diginamic.gestiondestransportsBack.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.gestiondestransportsBack.dto.UserDto;
import fr.diginamic.gestiondestransportsBack.modeles.User;
import fr.diginamic.gestiondestransportsBack.security.MyUserDetails;
import fr.diginamic.gestiondestransportsBack.security.UserService;
import fr.diginamic.gestiondestransportsBack.security.modele.JwtRequest;
import fr.diginamic.gestiondestransportsBack.security.modele.JwtResponse;
import fr.diginamic.gestiondestransportsBack.security.utility.JWTUtility;


@RestController
@RequestMapping("/")
public class UserController {
	
    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
    	
    	System.out.println("Username : " + jwtRequest.getUsername());
    	System.out.println("Password : " + jwtRequest.getPassword());
    	try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);
        
        
        System.out.println("authentifaction done .");
        return  new JwtResponse(token);
    }
    
    
    @GetMapping("/user")
    @CrossOrigin
    public UserDto getUser(Authentication authentication) {
    	User user = MyUserDetails.getCurrentUser(authentication);
    	UserDto userDto = new UserDto(user);
    	return userDto;
    }

}
