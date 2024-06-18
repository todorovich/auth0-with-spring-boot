package net.todorovich.server;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    static class UnableToRedirectException extends RuntimeException
    {
        public UnableToRedirectException(IOException cause) { super(cause); }
    }

    @Component
    public static class MyLogoutHandler implements LogoutHandler
    {
        private final String issuer;
        private final String clientId;

        public MyLogoutHandler(@Value("${okta.oauth2.issuer}") String issuer,  @Value("${okta.oauth2.client-id}") String clientId)
        {
            this.issuer = issuer;
            this.clientId = clientId;
        }

        @Override
        public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        {
            try
            {
                String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
                response.sendRedirect(issuer + "v2/logout?client_id=" + clientId + "&returnTo=" + baseUrl);
            } catch (IOException e)
            {
                throw new UnableToRedirectException(e);
            }
        }
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http, @Autowired MyLogoutHandler logoutHandler)
       throws Exception
    {
        http.authorizeHttpRequests(
                authorize -> authorize
                   .requestMatchers("/", "/images/**").permitAll()
                   .anyRequest().authenticated()
            )
            .oauth2Login(withDefaults())
            .logout(logout -> logout.addLogoutHandler(logoutHandler));

        return http.build();
    }
}
