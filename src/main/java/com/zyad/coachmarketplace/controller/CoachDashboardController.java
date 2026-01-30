package com.zyad.coachmarketplace.controller;

import com.zyad.coachmarketplace.entity.CoachProfile;
import com.zyad.coachmarketplace.entity.User;
import com.zyad.coachmarketplace.repository.CoachProfileRepository;
import com.zyad.coachmarketplace.repository.UsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoachDashboardController {

    private UsersRepository usersRepository;
    private CoachProfileRepository coachProfileRepository;

    public CoachDashboardController(UsersRepository usersRepository, CoachProfileRepository coachProfileRepository) {
        this.usersRepository = usersRepository;
        this.coachProfileRepository = coachProfileRepository;
    }

    @GetMapping("/coach/dashboard")
    public String dashboard(Authentication auth, Model model) {
        String email = auth.getName(); // logged in email
        User user = usersRepository.findByEmail(email).orElseThrow();
        CoachProfile profile = coachProfileRepository.findByUser(user);
        model.addAttribute("coachProfile", profile);
        return "coach/coach-dashboard";
    }

}
