package com.example.demo.controllers;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    private String registration () {
        return "registration";
    }

    @PostMapping("/registration")
    private String addUser (User user, Map<String, Object> model){
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB !=null) {
            model.put("message", "User Exists!");
            return "/registration";
        }

        user.setActive(true);
        Set roles = new HashSet();
        roles.add(Role.USER);
        user.setRoles(roles);
        userRepo.save(user);

        return "redirect:/login";
    }
}
