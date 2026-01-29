package com.zyad.coachmarketplace.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "coach_profile")
public class CoachProfile {

    public CoachProfile(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String displayName;

    @Column(length = 2000)
    private String bio;

    private String city;
    private String state;
    private String country;

    private BigDecimal hourlyRate;

    // Each CoachProfile has exactly 1 user.
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true) // in CoachProfile, create a column called "user_id" which puts the owning user's id inside that column.
    //      User this field holds a User entity object
    private User user;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "CoachProfile{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                ", bio='" + bio + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", hourlyRate=" + hourlyRate +
                ", user=" + user +
                '}';
    }
}
