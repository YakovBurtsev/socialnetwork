package ru.yakovburtsev.socialnetwork.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.user.matcher.ModelMatcher;

import java.util.Objects;

/**
 * The class provides data for unit tests.
 * The same data also are used in populateUsersDb_H2.sql
 */
public class UserTestData {
    private static final Logger LOG = LoggerFactory.getLogger(UserTestData.class);

    public static final long START_SEQ = 100000;
    public static final long IVAN_ID = START_SEQ;
    public static final long PETR_ID = START_SEQ + 1;
    public static final long VASILIY_ID = START_SEQ + 2;
    public static final long OTHER_VASILIY_ID = START_SEQ + 3;

    public static User IVAN = new User.Builder()
            .id(IVAN_ID).name("Ivan").surname("Ivanov").email("ivan@yandex.ru").password("ivan123").build();
    public static User PETR = new User.Builder()
            .id(PETR_ID).name("Petr").surname("Petrov").email("petr@gmail.com").password("petr123").build();
    public static User VASILIY = new User.Builder()
            .id(VASILIY_ID).name("Vasiliy").surname("Ivanov").email("vasiliy@gmail.com").password("vasiliy123").build();
    public static User OTHER_VASILIY = new User.Builder()
            .id(OTHER_VASILIY_ID).name("Vasiliy").surname("Novikov").email("novikov@gmail.com").password("novikov123").build();

    public static final ModelMatcher<User> MATCHER = ModelMatcher.of(User.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getSurname(), actual.getSurname())
                            && Objects.equals(expected.getBirthday(), actual.getBirthday())
                            && Objects.equals(expected.getSex(), actual.getSex())
                            && Objects.equals(expected.getCity(), actual.getCity())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.getPassword(), actual.getPassword())
                    )
    );

}
