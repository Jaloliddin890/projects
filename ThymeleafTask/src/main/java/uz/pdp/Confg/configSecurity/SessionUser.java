package uz.pdp.Confg.configSecurity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.domains.AuthUser;

@Component
public class SessionUser
{
    public AuthUser getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails.authUser();
        }
        return null;
    }
    public Long getId(){
        return getUser().getId();
    }
}
