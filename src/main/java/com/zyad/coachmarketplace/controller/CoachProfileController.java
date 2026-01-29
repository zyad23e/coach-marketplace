package com.zyad.coachmarketplace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CoachProfileController {

    // show an empty coach form for client to fill out
    @GetMapping("/coach/profile/new")
    public String coach(){
        return "coach/profile-form";
    }

    // save new coach, "html form says when i am submitted, send a POST request here."
    // remember, when getmapping is called, the actual form for coach information is called from the profile-form.html,
    // when that form is submitted, the browser sends a POST request(this here method), which receives the input values from the form.
    @PostMapping("/coach/profile/new")
    public String newCoach(){
        return "coach/profile-form";
    }
}
