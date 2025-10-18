package ru.kuzdikenov.entity;

public class User {
    private Integer id;
    private String name;
    private String lastname;
    private String login;
    private String password;
    private String profilePicturePath;

    public User() {}

    public User(Integer id, String name, String lastname, String login, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
    }

    public User(String name, String lastname, String login, String password) {
        this.name = name;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
    }

    public User(String name, String lastname, String login, String password, String profilePicturePath) {
        this.name = name;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.profilePicturePath = profilePicturePath;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }
}
