package fr.diginamic.gestiondestransportsBack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.diginamic.gestiondestransportsBack.cruds.CrudUser;
import fr.diginamic.gestiondestransportsBack.modeles.User;
import fr.diginamic.gestiondestransportsBack.security.MyUserDetails;


public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private CrudUser userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }         
        return new MyUserDetails(user);
    }
 
}