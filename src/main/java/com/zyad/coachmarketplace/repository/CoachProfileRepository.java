package com.zyad.coachmarketplace.repository;

import com.zyad.coachmarketplace.entity.CoachProfile;
import com.zyad.coachmarketplace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// bridge between our java objects and the SQL database, automatically performs SQL operations for us!
public interface CoachProfileRepository extends JpaRepository<CoachProfile, Integer> {
    CoachProfile findByUser(User user);
    List<CoachProfile> findAllByOrderByIdDesc();
}
