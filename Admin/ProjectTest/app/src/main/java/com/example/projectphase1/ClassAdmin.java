package com.example.projectphase1;

public class ClassAdmin {

    private String admin_Name;
    private String admin_email;
    private String admin_mobileNum;
    private  String admin_userName;
    private String admin_Pass;

    public ClassAdmin() {}

    public ClassAdmin(String admin_Name, String admin_email, String admin_mobileNum, String admin_userName, String admin_Pass) {
        this.admin_Name = admin_Name;
        this.admin_email = admin_email;
        this.admin_mobileNum = admin_mobileNum;
        this.admin_userName = admin_userName;
        this.admin_Pass = admin_Pass;
    }

    public String getAdmin_Name() {
        return admin_Name;
    }

    public void setAdmin_Name(String admin_Name) {
        this.admin_Name = admin_Name;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    public String getAdmin_mobileNum() {
        return admin_mobileNum;
    }

    public void setAdmin_mobileNum(String admin_mobileNum) {
        this.admin_mobileNum = admin_mobileNum;
    }

    public String getAdmin_userName() {
        return admin_userName;
    }

    public void setAdmin_userName(String admin_userName) {
        this.admin_userName = admin_userName;
    }

    public String getAdmin_Pass() {
        return admin_Pass;
    }

    public void setAdmin_Pass(String admin_Pass) {
        this.admin_Pass = admin_Pass;
    }
}
