package com.unipi.findoctor.security;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
                        .defaultSuccessUrl("/index") // @TODO - IMPORTANT: must be changed to the correct url for each user
                        .loginProcessingUrl("/login")
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
}
