package pl.coderslab.charity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.model.Users;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.UserService;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final DonationService donationService;

    public UserController(UserService userService, DonationService donationService) {
        this.userService = userService;
        this.donationService = donationService;
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

    @RequestMapping("/donation/list")
    public String donationList(Model model, Authentication auth){
        model.addAttribute("donations",donationService.findAllByUser(userService.findByUsername(auth.getName())));
        return "donation/donationList";
    }

    @RequestMapping("/edit")
    public String editUserForm(Model model,Authentication auth){
        model.addAttribute("user",userService.findByUsername(auth.getName()));
        return "users/editProfile";
    }

    @PostMapping("/edit")
    public String editUser(Users user){
        Users userToChange=userService.getById(user.getId());
        user.setRoles(userToChange.getRoles());
        user.setVerifyCode(null);
        userService.editUser(user);
        return "redirect:/";
    }

    @RequestMapping("/{id}/changePassword")
    public String changePassword(@PathVariable("id")Long id){
        Users user= userService.getById(id);
        user.setVerifyCode();
        userService.editUser(user);
        return "redirect:/changePassword/user/"+id+"/"+user.getVerifyCode();
    }
}
