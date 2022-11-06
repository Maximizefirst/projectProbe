package com.group7.project.controller;

import com.group7.project.model.UsersModel;
import com.group7.project.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    private UserService userService;

    public UsersController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UsersModel());
        return "register_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel){
        boolean exists = true;
        UsersModel existingUser = userService.findUserByUsername(usersModel.getUsername());

        if(existingUser == null){
            exists = false;
        }
        if(!exists){
            System.out.println("register request: " + usersModel);
            UsersModel registeredUser = userService.registerUser(usersModel.getUsername(), usersModel.getPassword(), usersModel.getEmail());
            if(registeredUser == null){
                return "error_page";
            }
            else{
                return "login_page";
            }
        }
        else{
            return "error_page";
        }
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UsersModel());
        return "login_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model){
        System.out.println("login request: " + usersModel);
        UsersModel authenticated = userService.authenticate(usersModel.getUsername(), usersModel.getPassword());
        if(authenticated != null){
            model.addAttribute("userLogin", authenticated.getUsername());
            model.addAttribute("userRole", authenticated.getRole());
            return "dashboard";
        }
        else{
            return "error_page";
        }
    }

    @GetMapping("/forgotPW")
    public String getForgotPWPage(){
        return "forgot_password";
    }

    @GetMapping("/keys")
    public String getKeyPage(){
        return "keys";
    }

    @GetMapping("/userDashboard")
    public String getUserDashboardPage(){
        return "user_dashboard";
    }

    @GetMapping("/testing")
    public String getTestPage(){
        return "testing";
    }
}
