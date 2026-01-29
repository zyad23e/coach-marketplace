package com.zyad.coachmarketplace.controller;

import com.zyad.coachmarketplace.entity.CoachProfile;
import com.zyad.coachmarketplace.repository.CoachProfileRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CoachProfileController {

    // by injecting this repository, it will allow our controller to call SQL
    // operations here such as save, find, etc...
    private CoachProfileRepository coachProfileRepository;

    public CoachProfileController(CoachProfileRepository coachProfileRepository){
        this.coachProfileRepository = coachProfileRepository;
    }

    // show an empty coach form for client to fill out
    @GetMapping("/coach/profile/new")
    public String coach(){
        return "coach/profile-form";
    }

    // save new coach, "html form says when i am submitted, send a POST request here."
    // remember, when getmapping is called, the actual form for coach information is called from the profile-form.html,
    // when that form is submitted, the browser sends a POST request(this here method), which receives the input values from the form.
    @PostMapping("/coach/profile/new")
    public String newCoach(CoachProfile coachProfile){ // form values submitted by coach are then set in coachProfile entity using setters methods.
        CoachProfile saved = coachProfileRepository.save(coachProfile); // reads the values back from coachProfile using getter methods and performs SQL to save to the database.
        return "redirect:/coach/profile/" + saved.getId();
    }

    @GetMapping("/coach/profile/{id}")
    // @PathVariable gives is the {id} int from the URL so we can use it.
    public String getCoachProfile(@PathVariable int id, Model model){
        CoachProfile profile = coachProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("Coach profile not found"));

        // the coachProfile profile entity is now saved into a Model factory, we can get information from this model by referencing the
        // object name in our html form which calls getter methods since the object is already populated.
        model.addAttribute("profile", profile);

        return "coach/profile-view";
    }
}
