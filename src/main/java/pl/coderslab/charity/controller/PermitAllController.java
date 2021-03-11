package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;


@Controller
public class PermitAllController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;

    public PermitAllController(InstitutionService institutionService, DonationService donationService, UserService userService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("institutions",institutionService.getAll());
        model.addAttribute("bagsSum",donationService.getSumBags());
        model.addAttribute("countDonations",donationService.countDonations());
        return "index";
    }

    @RequestMapping("/verify/{code}")
    public String verifyDone(@PathVariable("code") String code){
        userService.verifyAcc(code);
        return "verifyDone";
    }
}
