package com.zyad.coachmarketplace.repository;

import com.zyad.coachmarketplace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {
}
