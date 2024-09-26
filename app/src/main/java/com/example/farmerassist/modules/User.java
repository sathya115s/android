package com.example.farmerassist.modules;

public class User {

    private int id;
    private String name;
    private String email;
    private String usertype;
    private String email_verified_at;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsertype() {
        return usertype;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
