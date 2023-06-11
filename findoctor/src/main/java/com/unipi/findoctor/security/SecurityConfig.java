package com.unipi.findoctor.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

import static com.unipi.findoctor.constants.ControllerConstants.*;

@Configuration
@EnableWebSecurity
@NoArgsConstructor
public class SecurityConfig {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers(ADMIN_INDEX_URL_1, ADMIN_ROOT_URL + "**").hasAuthority("admin")
                .requestMatchers(PATIENT_INDEX_URL_1, PATIENT_ROOT_URL + "**").hasAuthority("patient")
                .requestMatchers(DOCTOR_INDEX_URL_1, DOCTOR_ROOT_URL + "**").hasAuthority("doctor")
                .requestMatchers(CONFIRMATION_URL).denyAll()
                .anyRequest().permitAll()
                .and()
                .formLogin(form -> form
                        .loginPage(LOGIN_URL)
                        .loginProcessingUrl(LOGIN_URL)
                        .successHandler(this::redirectBasedOnUserRole) // Custom success handler
                        .failureUrl(LOGIN_URL + "?error=true")
                        .permitAll()
                )
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL))
                                .addLogoutHandler(this::logout) // Custom logout handler
                                .permitAll()
                );
        return http.build();
    }

    private void redirectBasedOnUserRole(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // Redirect user based on his role
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String role = authentication.getAuthorities().iterator().next().getAuthority();
            switch (role) {
                case "admin" -> response.sendRedirect(ADMIN_ROOT_URL);
                case "patient" -> response.sendRedirect(PATIENT_ROOT_URL);
                case "doctor" -> response.sendRedirect(DOCTOR_ROOT_URL);
                default -> throw new IllegalStateException(); // if something goes wrong, throw server side error.
            }
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String url = LOGIN_URL; // Login page url, if not authenticated.
        if (!(authentication instanceof AnonymousAuthenticationToken || authentication == null)) {
            authentication.setAuthenticated(false); // Set user authentication to false
            request.getSession().invalidate(); // Invalidate user session
            url += "?logout=true"; // Append logout parameter to url
        }
        try {
            response.sendRedirect(url); // Redirect user to login page
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException(); // if something goes wrong, throw server side error.
        }
    }
}
