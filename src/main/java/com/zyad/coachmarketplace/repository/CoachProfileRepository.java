package com.zyad.coachmarketplace.repository;

import com.zyad.coachmarketplace.entity.CoachProfile;
import org.springframework.data.jpa.repository.JpaRepository;

// bridge between our java objects and the SQL database, automatically performs SQL operations for us!
public interface CoachProfileRepository extends JpaRepository<CoachProfile, Integer> {
}
