package com.zyad.coachmarketplace.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "coach_profile")
public class CoachProfile {

    public CoachProfile(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String displayName;
    private String bio;
    private BigDecimal hourlyRate;
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CoachProfile{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                ", bio='" + bio + '\'' +
                ", hourlyRate=" + hourlyRate +
                ", category='" + category + '\'' +
                '}';
    }
}
