package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Users;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;
import java.security.Principal;

@Controller
@RequestMapping("donation")
public class DonationController {

    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final UserService userService;

    public DonationController(DonationService donationService, CategoryService categoryService, InstitutionService institutionService, UserService userService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.userService = userService;
    }

    @RequestMapping("/new")
    public String showForm(Model model, Principal principal){
        Users user= userService.findByUsername(principal.getName());
        if (!user.isEnabled()){
            return "plzDoVerify";
        }else {
            model.addAttribute("categories", categoryService.getAll());
            model.addAttribute("donation", new Donation());
            model.addAttribute("institutions", institutionService.getAll());
            return "form";
        }
    }

    @PostMapping("/newDonation")
    public String addNewDonation(Donation donation){
        donationService.add(donation);
        return "redirect:/donation/new";

    }
}
