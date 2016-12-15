package ru.yakovburtsev.socialnetwork.friends;

import ru.yakovburtsev.socialnetwork.core.model.Request;
import ru.yakovburtsev.socialnetwork.friends.matcher.ModelMatcher;

import java.util.Objects;

/**
 * The class provides data for unit tests.
 * The same data also are used in populateUsersDb_H2.sql
 */
public class RequestTestData {

    public static Request REQUEST_0_1 = new Request(1L, 0L, 1L);
    public static Request REQUEST_0_2 = new Request(2L, 0L, 2L);
    public static Request REQUEST_0_3 = new Request(3L, 0L, 3L);
    public static Request REQUEST_4_0 = new Request(4L, 4L, 0L);
    public static Request REQUEST_5_0 = new Request(5L, 5L, 0L);
    public static Request REQUEST_5_6 = new Request(6L, 5L, 6L);
    public static Request REQUEST_3_4 = new Request(7L, 3L, 4L);
    public static Request REQUEST_3_5 = new Request(8L, 3L, 5L);


    public static final ModelMatcher<Request> MATCHER = ModelMatcher.of(Request.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getFromUserId(), actual.getFromUserId())
                            && Objects.equals(expected.getFromUserId(), actual.getFromUserId())
                    )
    );
}
