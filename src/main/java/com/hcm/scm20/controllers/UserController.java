package com.hcm.scm20.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/user")
public class UserController {


    //user dashboard page
    @RequestMapping(value = "/dashboard",method = RequestMethod.GET)
    public String userDashbaord() {
        System.out.println("User dashboard");
        return "user/dashboard";
    }

    //user profile page
    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String userProfile() {
        System.out.println("User profile");
        return "user/profile";
    }
    

}

