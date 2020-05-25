package com.example.projectphase1;

public class ClassProfile {

    private String profile_username;
    private String profile_name;
    private String profile_email;
    private String profile_phone;
    private String profile_adress;
    private int wallet_value;
    private int membership_status;

    public ClassProfile() {
    }

    public ClassProfile(String profile_username, String profile_name, String profile_email,
                        String profile_phone, String profile_adress, int wallet_value, int membership_status) {
        this.profile_username = profile_username;
        this.profile_name = profile_name;
        this.profile_email = profile_email;
        this.profile_phone = profile_phone;
        this.profile_adress = profile_adress;
        this.wallet_value = wallet_value;
        this.membership_status = membership_status;
    }

    public int getWallet_value() {
        return wallet_value;
    }

    public void setWallet_value(int wallet_value) {
        this.wallet_value = wallet_value;
    }

    public int getMembership_status() {
        return membership_status;
    }

    public void setMembership_status(int membership_status) {
        this.membership_status = membership_status;
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
