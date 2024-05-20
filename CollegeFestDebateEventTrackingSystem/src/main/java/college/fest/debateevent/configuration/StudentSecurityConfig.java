package college.fest.debateevent.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class StudentSecurityConfig {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers(HttpMethod.GET, "/students/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/students").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/students/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/students/**").hasRole("ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .httpBasic()
                .and()
            .csrf().disable(); // For simplicity, disable CSRF. Do not do this in production!

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("{noop}password").roles("USER").build());
        manager.createUser(User.withUsername("admin").password("{noop}admin").roles("USER", "ADMIN").build());
        return manager;
    }
}


