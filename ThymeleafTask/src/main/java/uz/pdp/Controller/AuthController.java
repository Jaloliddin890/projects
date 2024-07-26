package uz.pdp.Controller;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.daos.AuthUserDao;
import uz.pdp.domains.AuthUser;
import uz.pdp.dtos.RegisterDTO;

@Controller

public class AuthController {

    private final PasswordEncoder passwordEncoder;

    private final AuthUserDao authUserDao;

    public AuthController(PasswordEncoder passwordEncoder, AuthUserDao authUserDao) {
        this.passwordEncoder = passwordEncoder;
        this.authUserDao = authUserDao;
    }

    @GetMapping("/auth/login")
    public ModelAndView loginPage(@RequestParam(required = false) String error) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/login");
        modelAndView.addObject("Error", error);
        return modelAndView;
    }

    @GetMapping("/auth/logout")
    public ModelAndView logoutPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/logout");
        return modelAndView;
    }
    

    @GetMapping("/auth/register")
    public String registerPage() {
        return "auth/register";
    }

    @PostMapping("/auth/register")
    public String register(@ModelAttribute RegisterDTO dto) {
        AuthUser authUser = AuthUser.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .build();
        authUserDao.save(authUser);
        return "redirect:/auth/login";
    }
}
