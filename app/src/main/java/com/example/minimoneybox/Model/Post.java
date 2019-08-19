package com.example.minimoneybox.Model;

import com.google.gson.annotations.SerializedName;

public class Post {
    private String Email;
    private String Password;
    private String Idfa;

    public Post(String email, String password, String idfa) {
        Email = email;
        Password = password;
        Idfa = idfa;
    }
}
