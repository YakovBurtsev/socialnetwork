package ru.yakovburtsev.socialnetwork.friends;

import ru.yakovburtsev.socialnetwork.core.model.UserInfo;
import ru.yakovburtsev.socialnetwork.friends.matcher.ModelMatcher;

import java.util.Objects;

/**
 * The class provides data for unit tests.
 * The same data also are used in populateUsersDb_H2.sql
 */
public class FriendsTestData {

    public static final long START_SEQ = 100000;
    public static final long IVAN_ID = START_SEQ;
    public static final long PETR_ID = START_SEQ + 1;
    public static final long VASILIY_ID = START_SEQ + 2;
    public static final long VITALIY_ID = START_SEQ + 3;
    public static final long OLGA_ID = START_SEQ + 4;

    public static UserInfo IVAN = new UserInfo(IVAN_ID, "Ivan", "Ivanov");
    public static UserInfo PETR = new UserInfo(PETR_ID, "Petr", "Petrov");
    public static UserInfo VASILIY = new UserInfo(VASILIY_ID, "Vasiliy", "Vasiliev");
    public static UserInfo VITALIY = new UserInfo(VITALIY_ID, "Vitaliy", "Novikov");
    public static UserInfo OLGA = new UserInfo(OLGA_ID, "Olga", "Orlova");

    public static final ModelMatcher<UserInfo> MATCHER = ModelMatcher.of(UserInfo.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getSurname(), actual.getSurname())
                    )
    );
}
