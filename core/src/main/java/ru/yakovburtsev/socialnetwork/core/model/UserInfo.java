package ru.yakovburtsev.socialnetwork.core.model;

import java.io.Serializable;

/**
 * The class represents information about a user.
 */


public class UserInfo implements Serializable {

    private static final long SerialVersionUID = 1;

    private Long id;
    private String name;
    private String surname;

    public UserInfo() {
    }

    public UserInfo(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "UserInfo(" +
                "id=" + id +
                ", name=" + name +
                ", surname=" + surname +
                ')';
    }
}
