package ru.yakovburtsev.socialnetwork.core.util;

public enum Urls {
    USER,
    FRIENDS,
    PHOTO,
    MESSAGE;

    private String url;

    static {
        USER.url = "http://localhost:8081";
        FRIENDS.url = "http://localhost:8082";
        PHOTO.url = "http://localhost:8083";
        MESSAGE.url = "http://localhost:8084";
    }

    public String getUrl() {
        return url;
    }
}
