package uz.pdp.Controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.daos.AuthUserDao;
import uz.pdp.domains.AuthUser;
import uz.pdp.dtos.RegisterDTO;

@Controller
@ResponseBody
public class PrePostMethodLevelSecurityController {


    @GetMapping("/has_role_admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "has_role_admin";
    }

    @GetMapping("/has_role_user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String user() {
        return "has_role_user";
    }


}

