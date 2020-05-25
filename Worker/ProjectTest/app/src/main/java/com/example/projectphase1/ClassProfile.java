package com.example.projectphase1;

public class ClassProfile {

    private String profile_username;
    private String profile_name;
    private int speciality;
    private String profile_email;
    private String profile_phone;
    private String profile_adress;
    private String confirmedStatus;

    public ClassProfile() {
    }

    public ClassProfile(String profile_username, String profile_name, int speciality, String profile_email, String profile_phone, String profile_adress) {
        this.profile_username = profile_username;
        this.profile_name = profile_name;
        this.speciality = speciality;
        this.profile_email = profile_email;
        this.profile_phone = profile_phone;
        this.profile_adress = profile_adress;
        this.confirmedStatus = "no";
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

    public String getProfile_name() {
        return profile_name;
    }

    public void setProfile_name(String profile_name) {
        this.profile_name = profile_name;
    }

    public int getSpeciality() {
        return speciality;
    }

    public void setSpeciality(int speciality) {
        this.speciality = speciality;
    }

    public String getProfile_email() {
        return profile_email;
    }

    public void setProfile_email(String profile_email) {
        this.profile_email = profile_email;
    }

    public String getProfile_phone() {
        return profile_phone;
    }

    public void setProfile_phone(String profile_phone) {
        this.profile_phone = profile_phone;
    }

    public String getProfile_adress() {
        return profile_adress;
    }

    public void setProfile_adress(String profile_adress) {
        this.profile_adress = profile_adress;
    }
}
