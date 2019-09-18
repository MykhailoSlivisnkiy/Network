package com.project.one.controller;

import com.project.one.domain.Message;
import com.project.one.domain.User;
import com.project.one.repository.MessageRepo;
import com.project.one.repository.UserRepo;
import com.project.one.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

//@PreAuthorize("hasAuthority('ADMIN')") - дані методи будуть можливі тільки для користуачів з роллю ADMIN
@Controller
public class RegistrationController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @Autowired
    MessageRepo messageRepo;

    @GetMapping("/registration")
    public String registration(Model model){

        model.addAttribute("enter", "Enter");

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam() String username,
                               @RequestParam() String password,
                               @RequestParam() String email,
                               @AuthenticationPrincipal User user1,
                               Model model,
                               Model model1){
        User user = new User(username, password, email);

        if(!userService.addUser(user)){

            model1.addAttribute("enter", "User exist!");

            return "registration";
        }


        model.addAttribute("user", user);
        model.addAttribute("user1", user);

        return "user_page";
    }
}


//@Pathvariable - для отримання певного значення через URL - запит
