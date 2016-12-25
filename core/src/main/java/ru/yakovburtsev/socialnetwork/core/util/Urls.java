package ru.yakovburtsev.socialnetwork.core.util;

public enum Urls {
    USER,
    FRIENDS,
    PHOTO,
    MESSAGE;

    private String url;

    static {
        USER.url = "localhost:8081";
        FRIENDS.url = "localhost:8082";
        PHOTO.url = "localhost:8083";
        MESSAGE.url = "localhost:8084";
    }

    public String getUrl() {
        return url;
    }
}
