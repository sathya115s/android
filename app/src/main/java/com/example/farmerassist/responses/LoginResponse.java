package com.example.farmerassist.responses;

import com.example.farmerassist.modules.User;

public class LoginResponse {

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    private String message;
    private User user;

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
