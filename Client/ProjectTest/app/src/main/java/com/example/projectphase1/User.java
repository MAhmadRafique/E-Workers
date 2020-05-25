package com.example.projectphase1;

public class User {
    private String user_Email;
    private String user_Password;
    private int wallet_value;
    private int membership_status;
    private  String user_Name;
    private String user_UserName;

    public User(String user_Email, String user_Password, int wallet_value, int membership_status, String user_Name, String user_UserName) {
        this.user_Email = user_Email;
        this.user_Password = user_Password;
        this.wallet_value = wallet_value;
        this.membership_status = membership_status;
        this.user_Name = user_Name;
        this.user_UserName = user_UserName;
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

    public void setUser_Email(String user_Email) {
        this.user_Email = user_Email;
    }

    public void setUser_Password(String user_Password) {
        this.user_Password = user_Password;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public void setUser_UserName(String user_UserName) {
        this.user_UserName = user_UserName;
    }

    public String getUser_Email() {
        return user_Email;
    }

    public String getUser_Password() {
        return user_Password;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public String getUser_UserName() {
        return user_UserName;
    }

    public User()
    {

    }

}
