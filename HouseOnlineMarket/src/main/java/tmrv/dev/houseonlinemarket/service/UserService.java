package tmrv.dev.houseonlinemarket.service;

import org.springframework.stereotype.Service;
import tmrv.dev.houseonlinemarket.entities.User;
import tmrv.dev.houseonlinemarket.entities.domains.Role;
import tmrv.dev.houseonlinemarket.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findByRole(Role.USER);
    }

    public User blockActive(Long id){
        userRepository.findById(id).ifPresent(user -> {
            user.setActive(false);
            userRepository.save(user);
        });
        return userRepository.findById(id).orElse(null);
    }

    public User unblockActive(Long id){
        userRepository.findById(id).ifPresent(user -> {
            user.setActive(true);
            userRepository.save(user);
        });
        return userRepository.findById(id).orElse(null);
    }
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getId();
    }
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }





}
