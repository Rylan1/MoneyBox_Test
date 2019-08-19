package com.example.minimoneybox.Model;

import org.jetbrains.annotations.Nullable;

public class AllData {
    private String email;
    private String pssword;
    private String tolken;
    @Nullable
    private String username;
    public String getEmail() {
        return email;
    }

    public AllData(String email, String pssword,  String username) {
        this.email = email;
        this.pssword = pssword;

        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPssword() {
        return pssword;
    }

    public void setPssword(String pssword) {
        this.pssword = pssword;
    }

    public String getTolken() {
        return tolken;
    }

    public void setTolken(String tolken) {
        this.tolken = tolken;
    }
}
