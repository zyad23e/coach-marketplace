package com.zyad.coachmarketplace.controller;

import com.zyad.coachmarketplace.entity.Role;
import com.zyad.coachmarketplace.entity.User;
import com.zyad.coachmarketplace.repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UsersRepository usersRepository, PasswordEncoder passwordEncoder){
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(){
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user){
        if (usersRepository.findByEmail(user.getEmail()).isPresent()) {
            return "redirect:/register?error";
        }

        user.setRole(Role.COACH);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);

        return "redirect:/login?registered";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
}
