package com.zyad.coachmarketplace.repository;

import com.zyad.coachmarketplace.entity.CoachProfile;
import com.zyad.coachmarketplace.entity.CoachService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoachServiceRepository extends JpaRepository<CoachService, Integer> {
    List<CoachService> findByCoachProfileOrderByIdDesc(CoachProfile coachProfile);
    Optional<CoachService> findByIdAndCoachProfile(int id, CoachProfile coachProfile);
}
