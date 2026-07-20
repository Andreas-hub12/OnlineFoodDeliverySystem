package com.example.onlinefooddeliverysystem.session;

import java.io.*;

public class SessionManager {

    private static final String FILE_NAME = "session.dat";

    public static void saveSession(com.example.onlinefooddeliverysystem.session.SessionData sessionData) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(sessionData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SessionData loadSession() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (SessionData) in.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    public static void clearSession() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }
}