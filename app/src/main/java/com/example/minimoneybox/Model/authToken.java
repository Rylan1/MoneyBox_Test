package com.example.minimoneybox.Model;

public class authToken {
    private com.example.minimoneybox.Model.Session Session;

    public authToken(com.example.minimoneybox.Model.Session session) {
        Session = session;
    }

    public com.example.minimoneybox.Model.Session getSession() {
        return Session;
    }

    public void setSession(com.example.minimoneybox.Model.Session session) {
        Session = session;
    }
}
