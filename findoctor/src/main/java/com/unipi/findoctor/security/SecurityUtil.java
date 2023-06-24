package com.unipi.findoctor.security;

import com.unipi.findoctor.dto.UserDto;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityUtil {
    public UserDto getSessionUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return new UserDto(
                    authentication.getName(),
                    authentication.getAuthorities().toArray()[0].toString()
            );
        }
        return null;
    }
}
