package com.zyad.coachmarketplace.controller;

import com.zyad.coachmarketplace.entity.CoachProfile;
import com.zyad.coachmarketplace.entity.CoachService;
import com.zyad.coachmarketplace.entity.User;
import com.zyad.coachmarketplace.repository.CoachProfileRepository;
import com.zyad.coachmarketplace.repository.CoachServiceRepository;
import com.zyad.coachmarketplace.repository.UsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CoachServiceController {

    private final CoachServiceRepository coachServiceRepository;
    private final CoachProfileRepository coachProfileRepository;
    private final UsersRepository usersRepository;

    public CoachServiceController(CoachServiceRepository coachServiceRepository,
            CoachProfileRepository coachProfileRepository,
            UsersRepository usersRepository) {
        this.coachServiceRepository = coachServiceRepository;
        this.coachProfileRepository = coachProfileRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/coach/services/new")
    public String showServiceForm(Model model, Authentication authentication) {

        User user = usersRepository.findByEmail(authentication.getName()).orElseThrow();

        CoachProfile profile = coachProfileRepository.findByUser(user);
        if (profile == null) return "redirect:/coach/profile/new";

        model.addAttribute("service", new CoachService());
        return "coach/coach-service-form";
    }


    @PostMapping("/coach/services")
    public String createService(@ModelAttribute CoachService coachService,
            Authentication authentication) {

        User user = usersRepository.findByEmail(authentication.getName()).orElseThrow();

        CoachProfile profile = coachProfileRepository.findByUser(user);
        if (profile == null) return "redirect:/coach/profile/new";

        coachService.setCoachProfile(profile);
        coachServiceRepository.save(coachService);

        return "redirect:/coach/services";
    }

    @GetMapping("/coach/services")
    public String myServices(Model model, Authentication authentication) {

        User user = usersRepository.findByEmail(authentication.getName()).orElseThrow();

        CoachProfile profile = coachProfileRepository.findByUser(user);
        if (profile == null) return "redirect:/coach/profile/new";

        List<CoachService> services = coachServiceRepository.findByCoachProfileOrderByIdDesc(profile);
        model.addAttribute("services", services);

        return "coach/coach-services";
    }

    @GetMapping("/coach/services/{id}/edit")
    public String editService(@PathVariable int id,
            Model model,
            Authentication authentication) {

        User user = usersRepository.findByEmail(authentication.getName()).orElseThrow();

        CoachProfile profile = coachProfileRepository.findByUser(user);
        if (profile == null) return "redirect:/coach/profile/new";

        Optional<CoachService> serviceOpt = coachServiceRepository.findByIdAndCoachProfile(id, profile);
        if (serviceOpt.isEmpty()) return "redirect:/coach/services";

        model.addAttribute("service", serviceOpt.get());
        return "coach/coach-service-edit";
    }

    @PostMapping("/coach/services/{id}/update")
    public String updateService(@PathVariable int id,
            @ModelAttribute CoachService form,
            Authentication authentication) {

        User user = usersRepository.findByEmail(authentication.getName()).orElseThrow();

        CoachProfile profile = coachProfileRepository.findByUser(user);
        if (profile == null) return "redirect:/coach/profile/new";

        Optional<CoachService> serviceOpt = coachServiceRepository.findByIdAndCoachProfile(id, profile);
        if (serviceOpt.isEmpty()) return "redirect:/coach/services";

        CoachService existing = serviceOpt.get();

        existing.setTitle(form.getTitle());
        existing.setCategory(form.getCategory());
        existing.setPrice(form.getPrice());
        existing.setDescription(form.getDescription());

        coachServiceRepository.save(existing);

        return "redirect:/coach/services";
    }

    @PostMapping("/coach/services/{id}/delete")
    public String deleteService(@PathVariable int id,
            Authentication authentication) {

        User user = usersRepository.findByEmail(authentication.getName()).orElseThrow();

        CoachProfile profile = coachProfileRepository.findByUser(user);
        if (profile == null) return "redirect:/coach/profile/new";

        Optional<CoachService> serviceOpt = coachServiceRepository.findByIdAndCoachProfile(id, profile);
        serviceOpt.ifPresent(coachServiceRepository::delete);

        return "redirect:/coach/services";
    }
}
