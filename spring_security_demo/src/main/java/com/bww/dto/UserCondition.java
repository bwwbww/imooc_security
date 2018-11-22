package com.bww.dto;

public class UserCondition {
    private String username;
    private String userage;
    private String usersex;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserage() {
        return userage;
    }

    public void setUserage(String userage) {
        this.userage = userage;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    @Override
    public String toString() {
        return "UserCondition{" +
                "username='" + username + '\'' +
                ", userage='" + userage + '\'' +
                ", usersex='" + usersex + '\'' +
                '}';
    }
}
