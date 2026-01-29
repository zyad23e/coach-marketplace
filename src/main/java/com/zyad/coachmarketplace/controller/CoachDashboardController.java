package com.zyad.coachmarketplace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoachDashboardController {

    @GetMapping("/coach/dashboard")
    public String dashboard() {
        return "coach/coach-dashboard";
    }
}
