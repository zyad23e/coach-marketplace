package com.zyad.coachmarketplace.controller;

import com.zyad.coachmarketplace.entity.Role;
import com.zyad.coachmarketplace.entity.User;
import com.zyad.coachmarketplace.repository.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UsersRepository usersRepository;

    public UserController(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @GetMapping("/register")
    public String showRegistrationForm(){
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(User user){
        user.setRole(Role.COACH);
        User savedUser = usersRepository.save(user);
        return "redirect:/coach/dashboard";
    }
}
