package org.example.http;

import lombok.Getter;
import lombok.Setter;

public class SessionStorage {

    private static String sessionId;

    public static String getSessionId() {
        return sessionId;
    }

    public static void setSessionId(String sessionId) {
        SessionStorage.sessionId = sessionId;
    }
}
