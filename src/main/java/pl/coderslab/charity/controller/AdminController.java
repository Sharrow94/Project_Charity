package pl.coderslab.charity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.Users;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final InstitutionService institutionService;
    private final UserService userService;
    private final RoleRepository roleRepository;

    public AdminController(InstitutionService institutionService, UserService userService, RoleRepository roleRepository) {
        this.institutionService = institutionService;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @RequestMapping("/home")
    public String homePage(){
        return "admin/homePage";
    }

    @RequestMapping("/institution/list")
    public String institutionList(Model model){
        model.addAttribute("institutions",institutionService.getAll());
        return "institution/list";
    }

    @RequestMapping("/institution/add")
    public String addShowForm(Model model){
        model.addAttribute("institution",new Institution());
        return "institution/add";
    }

    @PostMapping("/institution/add")
    public String addInstitution(Institution institution){
        institutionService.add(institution);
        return "redirect:/admin/institution/list";
    }

    @RequestMapping("institution/delete/{id}")
    public String deleteInst(@PathVariable("id") Long id){
        institutionService.delete(id);
        return "redirect:/admin/institution/list";
    }

    @RequestMapping("/institution/edit/{id}")
    public String editShowForm(@PathVariable("id") Long id, Model model){
        model.addAttribute(institutionService.getById(id));
        return "institution/edit";
    }

    @PostMapping("/institution/edit")
    public String editInst(Institution institution){
        institutionService.add(institution);
        return "redirect:/admin/institution/list";
    }

    @RequestMapping("/user/list")
    public String usersList(Model model){
        model.addAttribute("users",userService.findAllByRoleUser());
        return "users/userList";
    }

    @RequestMapping("/admin/list")
    public String adminList(Model model){
        model.addAttribute("admins",userService.findAllByRoleAdmin());
        return "admin/adminList";
    }


    @RequestMapping("/admin/edit/{id}")
    public String editShowForm(Model model, @PathVariable("id") Long id){
        model.addAttribute("admin",userService.getById(id));
        return "admin/editAdmin";
    }

    @RequestMapping("/makeAdmin/{id}")
    public String makeAdmin(@PathVariable("id") Long id){
        userService.makeAdmin(id);
        Users user=userService.getById(id);
        if (user.getRoles().contains(roleRepository.findByName("ROLE_ADMIN"))){
            return "redirect:/admin/admin/list";
        }
        return "redirect:/admin/user/list";
    }

    @RequestMapping("/user/block/{id}")
    public String block(@PathVariable("id") Long id){
        userService.blockUser(id);
        return "redirect:/admin/user/list";
    }

    @RequestMapping("/admin/delete/{id}")
    public String deleteAdmin(@PathVariable("id") Long id, Authentication auth){
        Users user=userService.findByUsername(auth.getName());
        if (user.getId().equals(id)){
            return "redirect:/admin/admin/list";
        }
        userService.delete(id);
        return "redirect:/admin/admin/list";
    }

    @RequestMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/admin/admin/list";
    }

}
