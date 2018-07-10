package com.katieoshea.greatideas.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.katieoshea.greatideas.models.User;
import com.katieoshea.greatideas.services.UserServ;
import com.katieoshea.greatideas.validator.UserValidator;

@Controller
public class UserController {
    private UserServ uServ;
    private UserValidator uValid;
    public UserController(UserServ uServ, UserValidator uValid) {
        this.uServ = uServ;
        this.uValid = uValid;
    }
	
    @RequestMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model, @Valid @ModelAttribute("user") User user, BindingResult result) {
    	if(logout != null) {
            model.addAttribute("logout", "Logout Successful!");
        }
        if(error != null) {
            model.addAttribute("logError", "Invalid credentials, please try again.");
        }
        return "logreg";
    }
        
    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result) {
        uValid.validate(user, result);
        if(result.hasErrors()) {
            return "logreg";
        }
        else {
            uServ.save(user);
            return "redirect:/login";
        }
    }   

    @RequestMapping(value= {"/", "/home"})
    public String index() {
    	return "redirect:/ideas";
    }
}
