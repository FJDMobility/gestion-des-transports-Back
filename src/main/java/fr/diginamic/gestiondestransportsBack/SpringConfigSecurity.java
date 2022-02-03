package fr.diginamic.gestiondestransportsBack;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fr.diginamic.gestiondestransportsBack.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)
public class SpringConfigSecurity extends WebSecurityConfigurerAdapter {
 
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//            .antMatchers("/").hasAnyAuthority("USER", "ADMIN")
//            .antMatchers("/new").hasAnyAuthority("ADMIN", "USER")
//            .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "USER")
//            .antMatchers("/delete/**").hasAuthority("ADMIN")
//            .anyRequest().authenticated()
//            .and()
//            .formLogin().permitAll()
//            .and()
//            .logout().permitAll()
//            .and()
//            .exceptionHandling().accessDeniedPage("/403")
//            .and().httpBasic().and().csrf().disable()
//            ;
    	
    	 http.csrf().disable()
         .formLogin().loginProcessingUrl("/login").and()
         .logout().logoutUrl("/logout").invalidateHttpSession(true).and()
         .authorizeRequests()
         .antMatchers("/login").permitAll()
         .antMatchers("/logout").permitAll()
         // authorise toutes les connexions REST sur /api
         .antMatchers("/**").permitAll()
         .anyRequest().authenticated().and().httpBasic(); 
    }
} 
