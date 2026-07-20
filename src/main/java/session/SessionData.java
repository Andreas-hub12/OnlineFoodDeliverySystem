package com.example.onlinefooddeliverysystem.session;


import com.example.onlinefooddeliverysystem.model.User;
import java.io.Serializable;

public class SessionData implements Serializable {
    private static final long serialVersionUID = 1L;

    private User user;
    private String currentScreen;

    public SessionData(User user, String currentScreen) {
        this.user = user;
        this.currentScreen = currentScreen;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentScreen(String currentScreen) {
        this.currentScreen = currentScreen;
    }
}