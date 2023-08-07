package org.example;

public class User {
    private final int id;
    private final String guid;
    private final String name;

    public User(int id, String guid, String name) {
        this.id = id;
        this.guid = guid;
        this.name = name;
    }

    public int getUserId() {
        return id;
    }

    public String getUserGuid() {
        return guid;
    }

    public String getUserName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + id +
                ", userGuid='" + guid + '\'' +
                ", userName='" + name + '\'' +
                '}';
    }
}
