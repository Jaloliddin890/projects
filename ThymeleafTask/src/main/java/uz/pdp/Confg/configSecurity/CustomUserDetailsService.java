package uz.pdp.Confg.configSecurity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.daos.AuthPermissionDao;
import uz.pdp.daos.AuthRoleDao;
import uz.pdp.daos.AuthUserDao;
import uz.pdp.domains.AuthPermission;
import uz.pdp.domains.AuthRoles;
import uz.pdp.domains.AuthUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserDao authUserDao;
    private final AuthRoleDao authRoleDao;
    private final AuthPermissionDao authPermissionDao;

    public CustomUserDetailsService(AuthUserDao authUserDao, AuthRoleDao authRoleDao, AuthPermissionDao authPermissionDao) {
        this.authUserDao = authUserDao;
        this.authRoleDao = authRoleDao;
        this.authPermissionDao = authPermissionDao;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authUserDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("USERNAME NOT FOUND "));
        Long userID = authUser.getId();
        List<AuthRoles> roles = authRoleDao.findByUserID(userID);
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (AuthRoles role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getCode()));
            List<AuthPermission> permissions = authPermissionDao.findByRoleID(role.getId());
            for (AuthPermission permission : permissions) {
                authorities.add(new SimpleGrantedAuthority(permission.getCode()));
            }
        }


        return new User(authUser.getUsername(), authUser.getPassword(), authorities);

    }
}
