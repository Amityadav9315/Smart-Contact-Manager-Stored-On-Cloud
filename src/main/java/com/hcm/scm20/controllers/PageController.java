

package com.hcm.scm20.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcm.scm20.entities.User;
import com.hcm.scm20.forms.UserForms;
import com.hcm.scm20.forms.Userform;


@Controller
public class PageController {
    @RequestMapping("/home")
    public String home() {
        System.out.println("Home page handler");
        return "home";                
    }

    // About route
    @RequestMapping("/about")
    public String aboutPage(){
        System.out.println("About page loading");
        return "about";
    }



    // Services
    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("Services page loading");
        return "services";
    } 

    // Contact page
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    // Login
    @GetMapping("/login")
    public String login() {
        return "login";
    }                         

    // Register
    @GetMapping("/register")
    public String register(Model model) {
        UserForms userform = new UserForms();
        model.addAttribute("userForm", userform);  // Corrected syntax for model attribute
        return "register";
    }

    // Processing register
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForms userForm){
        System.out.println("Processing registration");
        // Fetch data, validate form data, save to database, and add a success message if needed
        return "redirect:/register";  // Redirect to register page
    }
}
