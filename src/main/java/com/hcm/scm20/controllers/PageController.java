package com.hcm.scm20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcm.scm20.entities.User;
import com.hcm.scm20.forms.UserForm;
import com.hcm.scm20.helpers.Message;
import com.hcm.scm20.helpers.MessageType;
import com.hcm.scm20.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PageController {

     @Autowired
     private UserService userService;

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
        model.addAttribute("userForm",new UserForm());
        return "register";
    }
    
    

    // Processing register
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,HttpSession session){
        System.out.println("Processing registration");
        System.out.println(userForm);
        // Fetch data, validate form data, , and add a success message if needed
        if(rBindingResult.hasErrors()){
            return "register";
        }
        
        //save to database
        //userservice
        // UserForm--> User
        
                //User user=User.builder()
                  //       .name(userForm.getName())
                    //     .email(userForm.getEmail())
                      //   .password(userForm.getPassword())
                        // .about(userForm.getAbout())
                       //  .phoneNumber(userForm.getPhoneNumber())
                        // .profilePic(null)
               //  .build();
               User user=new User();
               user.setName(userForm.getName());
               user.setEmail(userForm.getEmail());
               user.setPassword(userForm.getPassword());
               user.setAbout(userForm.getAbout());
               user.setPhoneNumber(userForm.getPhoneNumber());
               user.setProfilePic(null);


                 User savedUser=userService.saveUser(user);
                 System.out.println("user saved");
        
              // message ="registration succsessful"
              //add the message
              Message message=Message.builder().content("Registration Successful").type(MessageType.green).build();
              session.setAttribute("message", message);
        
        return "redirect:/register";  // Redirect to register page
    }
}

