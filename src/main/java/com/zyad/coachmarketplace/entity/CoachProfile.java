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

    @Column(length = 2000)
    private String bio;

    private String location;
    private String contactEmail;
    private String contactNumber;

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Column(name = "profile_image_path")
    private String profileImagePath;

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    private BigDecimal hourlyRate;

    // Each CoachProfile has exactly 1 user.
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true) // in CoachProfile, create a column called "user_id" which puts the owning user's id inside that column.
    //      User this field holds a User entity object
    private User user;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    @Override
    public String toString() {
        return "CoachProfile{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                ", bio='" + bio + '\'' +
                ", location='" + location + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", profileImagePath='" + profileImagePath + '\'' +
                ", hourlyRate=" + hourlyRate +
                ", user=" + user +
                '}';
    }
}
