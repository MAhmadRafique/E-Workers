package com.example.projectphase1;

public class ClassWorker {
    private String speciality;
    private String user_Name;
    private String user_Email;
    private String user_UserName;
    private String user_Password;

    public ClassWorker(String speciality, String user_Name, String user_Email, String user_UserName, String user_Password) {
        this.speciality = speciality;
        this.user_Name = user_Name;
        this.user_Email = user_Email;
        this.user_UserName = user_UserName;
        this.user_Password = user_Password;
    }


    public ClassWorker() {

    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_Email() {
        return user_Email;
    }

    public void setUser_Email(String user_Email) {
        this.user_Email = user_Email;
    }

    public String getUser_UserName() {
        return user_UserName;
    }

    public void setUser_UserName(String user_UserName) {
        this.user_UserName = user_UserName;
    }

    public String getUser_Password() {
        return user_Password;
    }

    public void setUser_Password(String user_Password) {
        this.user_Password = user_Password;
    }
}
