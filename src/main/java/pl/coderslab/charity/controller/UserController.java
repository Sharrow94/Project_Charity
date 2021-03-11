package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.model.Users;
import pl.coderslab.charity.service.UserService;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new Users());
        return "register";
    }

    @PostMapping("/registerDone")
    public String registerDone(Users user, @RequestParam("password2") String password2,HttpServletRequest request){
        if (!user.getPassword().equals(password2)){
            return "redirect:/user/register";
        }
        userService.add(user);
        userService.saveUser(user,getSiteURL(request));
        return "plzDoVerify";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
