package uz.pdp.config.configSecurity;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import uz.pdp.authuser.AuthUser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public record CustomUserDetailsService(AuthUserDao authUserDao) implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authUserDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("USERNAME NOT FOUND "));
        return new CustomUserDetails(authUser);

    }
}
