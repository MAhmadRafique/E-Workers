package com.example.projectphase1;

public class ClassWorkerProfile {
    private String speciality;
    private String profile_name;
    private String profile_email;
    private String confirmedStatus;
    private String profile_username;
    private String profile_adress;
    private String profile_phone;

    public ClassWorkerProfile(String speciality, String profile_name, String profile_email, String confirmedStatus, String profile_username, String profile_adress, String profile_phone) {
        this.speciality = speciality;
        this.profile_name = profile_name;
        this.profile_email = profile_email;
        this.confirmedStatus = confirmedStatus;
        this.profile_username = profile_username;
        this.profile_adress = profile_adress;
        this.profile_phone = profile_phone;
    }

    public ClassWorkerProfile() {
    }

    public String getProfile_phone() {
        return profile_phone;
    }

    public void setProfile_phone(String profile_phone) {
        this.profile_phone = profile_phone;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getProfile_name() {
        return profile_name;
    }

    public void setProfile_name(String profile_name) {
        this.profile_name = profile_name;
    }

    public String getProfile_email() {
        return profile_email;
    }

    public void setProfile_email(String profile_email) {
        this.profile_email = profile_email;
    }

    public String getConfirmedStatus() {
        return confirmedStatus;
    }

    public void setConfirmedStatus(String confirmedStatus) {
        this.confirmedStatus = confirmedStatus;
    }

    public String getProfile_username() {
        return profile_username;
    }

    public void setProfile_username(String profile_username) {
        this.profile_username = profile_username;
    }

    public String getProfile_adress() {
        return profile_adress;
    }

    public void setProfile_adress(String profile_adress) {
        this.profile_adress = profile_adress;
    }
}
