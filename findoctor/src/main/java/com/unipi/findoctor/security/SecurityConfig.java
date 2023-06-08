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
import org.springframework.security.core.GrantedAuthority;
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
                .requestMatchers(
                        "/", "/index", "/login", "/register", "/admin-doctor/css/**",
                        "/visitor-patient/css/**", "/visitor-patient/scss/**",
                        "/admin-doctor/js/**", "/admin-doctor/css/**", "/admin-doctor/scss/**")
                .permitAll()
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(this::redirectBasedOnUserRole) // Redirect to different pages based on user role
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/login?logout") // Redirect to login page after successful logout
                                .invalidateHttpSession(true) // Invalidate user session
                                .clearAuthentication(true) // Clear user authentication
                                .permitAll()
                );
        return http.build();
    }

    private void redirectBasedOnUserRole(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals("admin")) {
                    response.sendRedirect(ADMIN_ROOT_URL);
                    return;
                }
                if (authority.getAuthority().equals("doctor")) {
                    response.sendRedirect(DOCTOR_ROOT_URL);
                    return;
                }
                if (authority.getAuthority().equals("patient")) {
                    response.sendRedirect(PATIENT_ROOT_URL);
                    return;
                }
                throw new IllegalStateException(); // if something goes wrong, throw server side error.
            }
        }
    }
}
