package pl.coderslab.charity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Users;
import pl.coderslab.charity.service.*;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("donation")
public class DonationController {

    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final UserService userService;
    private final MailService mailService;

    public DonationController(DonationService donationService, CategoryService categoryService, InstitutionService institutionService, UserService userService, MailService mailService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.userService = userService;
        this.mailService = mailService;
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
    public String addNewDonation(Donation donation,Authentication auth){
        donation.setUser(userService.findByUsername(auth.getName()));
        donation.setCreateDate(LocalDate.now());
        donationService.add(donation);
        List<Users> users=userService.findAllByRoleAdmin();
        users.forEach(mailService::sendNotificationAboutGift);
        return "redirect:/donation/new";

    }

    @RequestMapping("/received/{id}")
    public String changeStatus(@PathVariable("id")Long id){
        Donation donation=donationService.getById(id);
        if (!donation.isReceived()){
            donation.setReceived(true);
            donation.setPickUpDate(LocalDate.now());
            donationService.add(donation);
        }
        return "redirect:/user/donation/list";
    }

    @RequestMapping("/{id}")
    public String showDetails(@PathVariable("id")Long id,Model model){
        model.addAttribute(donationService.getById(id));
        return "donation/details";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        Donation donation=donationService.getById(id);
        if (!donation.isReceived()){
            donationService.delete(id);
        }
        return "redirect:/user/donation/list";
    }
}
