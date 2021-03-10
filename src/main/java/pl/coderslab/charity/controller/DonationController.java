package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

@Controller
@RequestMapping("donation")
public class DonationController {

    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;

    public DonationController(DonationService donationService, CategoryService categoryService, InstitutionService institutionService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
    }

    @RequestMapping("/new")
    public String showForm(Model model){
        model.addAttribute("categories",categoryService.getAll());
        model.addAttribute("donation",new Donation());
        model.addAttribute("institutions",institutionService.getAll());
        return "form";
    }

    @PostMapping("/newDonation")
    public String addNewDonation(Donation donation){
        donationService.add(donation);
        return "redirect:/donation/new";

    }
}
