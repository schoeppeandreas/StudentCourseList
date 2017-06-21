package fi.haagahelia.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        
    	http
    		.authorizeRequests().antMatchers("/css/**").permitAll() // Enable css when logged out
    			.and()
            .authorizeRequests()
                .antMatchers("/", "add", "delete/{id}", "save", "students", "/addStudentCourse/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/students")
                .permitAll()
            	.and()
            .logout()
            	.permitAll()
            	.and()
            .httpBasic()
            	.and()
            .csrf().disable(); //Disable CSRF
    	
    	
    	//https://springframework.guru/using-the-h2-database-console-in-spring-boot-with-spring-security/
    	http.csrf().disable();
    	http.headers().frameOptions().disable();
        
        
    }
     
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {  
    	auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
    }
}