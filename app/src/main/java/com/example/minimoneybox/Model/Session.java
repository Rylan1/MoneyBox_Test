package com.example.minimoneybox.Model;

public class Session {
    private String BearerToken;

    public Session(String bearerToken) {
        BearerToken = bearerToken;
    }

    public String getBearerToken() {
        return BearerToken;
    }

    public void setBearerToken(String bearerToken) {
        BearerToken = bearerToken;
    }
}
