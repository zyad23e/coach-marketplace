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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Controller
public class CoachProfileController {

    private final CoachProfileRepository coachProfileRepository;
    private final CoachServiceRepository coachServiceRepository;
    private final UsersRepository usersRepository;

    public CoachProfileController(CoachProfileRepository coachProfileRepository,
            CoachServiceRepository coachServiceRepository,
            UsersRepository usersRepository) {
        this.coachProfileRepository = coachProfileRepository;
        this.coachServiceRepository = coachServiceRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/coach/profile/new")
    public String coach(Model model, Authentication authentication) {
        User user = usersRepository.findByEmail(authentication.getName()).orElseThrow();
        CoachProfile existing = coachProfileRepository.findByUser(user);
        model.addAttribute("coachProfile", existing);
        return "coach/profile-form";
    }

    @PostMapping("/coach/profile")
    public String createProfile(@ModelAttribute CoachProfile coachProfile,
            @RequestParam("profileImage") MultipartFile profileImage,
            Authentication authentication) {

        try {
            User user = usersRepository.findByEmail(authentication.getName()).orElseThrow();
            CoachProfile existing = coachProfileRepository.findByUser(user);

            coachProfile.setUser(user);
            if (existing != null) {
                coachProfile.setId(existing.getId()); // needs setId in CoachProfile
            }

            if (profileImage != null && !profileImage.isEmpty()) {
                Path uploadDir = Paths.get("uploads/profile-pics");
                Files.createDirectories(uploadDir);

                String originalName = profileImage.getOriginalFilename();
                String ext = "";
                if (originalName != null && originalName.contains(".")) {
                    ext = originalName.substring(originalName.lastIndexOf("."));
                }

                String filename = UUID.randomUUID().toString() + ext;
                Path filePath = uploadDir.resolve(filename);

                Files.copy(profileImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                coachProfile.setProfileImagePath("/uploads/profile-pics/" + filename);
            } else {
                if (existing != null) {
                    coachProfile.setProfileImagePath(existing.getProfileImagePath());
                }
            }

            coachProfileRepository.save(coachProfile);

        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/coach/profile/new";
        }

        return "redirect:/coach/dashboard";
    }

    @GetMapping("/coach/profile/{id}")
    public String getCoachProfile(@PathVariable int id, Model model) {

        CoachProfile profile = coachProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coach profile not found"));

        List<CoachService> services =
                coachServiceRepository.findByCoachProfileOrderByIdDesc(profile);

        model.addAttribute("profile", profile);
        model.addAttribute("services", services);

        return "coach/profile-view";
    }
}
