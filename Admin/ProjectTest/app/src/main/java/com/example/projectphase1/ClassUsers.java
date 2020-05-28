package com.example.projectphase1;

public class ClassUsers {
    private String memberShipStatus;
    private String name;
    private String email;
    private String password;
    private String userName;
    private String walletValue;

    public ClassUsers(String memberShipStatus, String name, String email, String password, String userName, String walletValue) {
        this.memberShipStatus = memberShipStatus;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.walletValue = walletValue;
    }

    public String getMemberShipStatus() {
        return memberShipStatus;
    }

    public void setMemberShipStatus(String memberShipStatus) {
        this.memberShipStatus = memberShipStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWalletValue() {
        return walletValue;
    }

    public void setWalletValue(String walletValue) {
        this.walletValue = walletValue;
    }
}
