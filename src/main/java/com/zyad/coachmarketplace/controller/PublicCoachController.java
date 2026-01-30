package com.zyad.coachmarketplace.controller;

import com.zyad.coachmarketplace.repository.CoachProfileRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicCoachController {

    private final CoachProfileRepository coachProfileRepository;

    public PublicCoachController(CoachProfileRepository coachProfileRepository) {
        this.coachProfileRepository = coachProfileRepository;
    }

    @GetMapping("/coaches")
    public String allCoaches(Model model) {
        model.addAttribute("coaches",
                coachProfileRepository.findAllByOrderByIdDesc());
        return "public/coaches";
    }
}
