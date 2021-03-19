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
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.MailService;
import pl.coderslab.charity.service.UserService;

import javax.servlet.http.HttpServletRequest;


@Controller
public class PermitAllController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;
    private final MailService mailService;

    public PermitAllController(InstitutionService institutionService, DonationService donationService, UserService userService, MailService mailService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
        this.mailService = mailService;
    }

    @RequestMapping("/")
    public String homeAction(Model model, Authentication auth){
        model.addAttribute("institutions",institutionService.getAll());
        model.addAttribute("bagsSum",donationService.getSumBags());
        model.addAttribute("countDonations",donationService.countDonations());
        if (auth!=null) {
            model.addAttribute("currentUser", userService.findByUsername(auth.getName()));
        }
        return "index";
    }

    @RequestMapping("/verify/{code}")
    public String verifyDone(@PathVariable("code") String code){
        userService.verifyAcc(code);
        return "verifyDone";
    }

    @RequestMapping("/resetPassword/step1")
    public String showFormEmail(){
        return "users/getEmail";
    }

    @PostMapping("/resetPassword/step1")
    public String sendEmail(@RequestParam("email")String email, HttpServletRequest request){
        Users user=userService.findByEmail(email);
        if (user==null){
            return "users/wrongEmail";
        }else {
            userService.add(user);
            mailService.sendTokenToResetPass(user,getSiteURL(request));
            return "users/goodEmail";
        }
    }

    @RequestMapping("/resetPassword/step2/{token}")
    public String checkToken(@PathVariable("token") String token){
        Users user=userService.findByVerifyCode(token);
        if (user!=null){
            return "redirect:/changePassword/user/"+user.getId()+"/"+token;
        }
        return "redirect:/";
    }

    @RequestMapping("/changePassword/user/{id}/{token}")
    public String changePassForm(@PathVariable("id")Long id,@PathVariable("token")String token,HttpServletRequest request){
        Users user= userService.getById(id);
        if (user.getVerifyCode().equals(token)){
            request.setAttribute("id",user.getId());
            request.setAttribute("token",token);
            return "users/changePassword";
        }
        return "redirect:/login";
    }

    @PostMapping("/passwordChanged")
    public String changePass(@RequestParam("id")Long id,
                             @RequestParam("token")String token,
                             @RequestParam("password1")String password1,
                             @RequestParam("password2")String password2){
        Users user= userService.getById(id);
        if (user.getVerifyCode().equals(token) && password1.equals(password2) && user.getVerifyCode()!=null){
            userService.changePassword(user,password1);
            return "redirect:/user/login";
        }
        return "redirect:/";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
