package ru.yakovburtsev.socialnetwork.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yakovburtsev.socialnetwork.core.model.Role;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;
import ru.yakovburtsev.socialnetwork.user.matcher.ModelMatcher;
import ru.yakovburtsev.socialnetwork.user.util.PasswordUtil;

import java.util.Objects;

/**
 * The class provides data for unit tests.
 * The same data also are used in populateDb_H2.sql
 */
public class UserTestData {
    private static final Logger LOG = LoggerFactory.getLogger(UserTestData.class);

    public static final long START_SEQ = 100000;
    public static final long IVAN_ID = START_SEQ;
    public static final long PETR_ID = START_SEQ + 1;
    private static final long VASILIY_ID = START_SEQ + 2;
    private static final long OTHER_VASILIY_ID = START_SEQ + 3;

    public static final User IVAN = new User.Builder()
            .id(IVAN_ID).name("Ivan")
            .surname("Ivanov")
            .email("ivan@yandex.ru")
            .password("ivan123")
            .roles(Role.ROLE_USER)
            .build();

    public static final User PETR = new User.Builder()
            .id(PETR_ID)
            .name("Petr")
            .surname("Petrov")
            .email("petr@gmail.com")
            .password("petr123")
            .roles(Role.ROLE_USER)
            .build();

    public static final User VASILIY = new User.Builder()
            .id(VASILIY_ID)
            .name("Vasiliy")
            .surname("Ivanov")
            .email("vasiliy@gmail.com")
            .password("vasiliy123")
            .roles(Role.ROLE_USER)
            .build();

    public static final User OTHER_VASILIY = new User.Builder()
            .id(OTHER_VASILIY_ID)
            .name("Vasiliy")
            .surname("Novikov")
            .email("novikov@gmail.com")
            .password("novikov123")
            .roles(Role.ROLE_ADMIN)
            .build();


    public static final ModelMatcher<User> USER_MATCHER = ModelMatcher.of(User.class,
            (expected, actual) -> expected == actual ||
                    (comparePassword(expected.getPassword(), actual.getPassword())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getSurname(), actual.getSurname())
                            && Objects.equals(expected.getBirthday(), actual.getBirthday())
                            && Objects.equals(expected.getSex(), actual.getSex())
                            && Objects.equals(expected.getCity(), actual.getCity())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    )
    );

    public static final ModelMatcher<UserInfo> USER_INFO_MATCHER = ModelMatcher.of(UserInfo.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getSurname(), actual.getSurname())
                    )
    );

    private static boolean comparePassword(String rawOrEncodedPassword, String password) {
        if (PasswordUtil.isEncoded(rawOrEncodedPassword)) {
            return rawOrEncodedPassword.equals(password);
        } else if (!PasswordUtil.isMatch(rawOrEncodedPassword, password)) {
            LOG.error("Password " + password + " doesn't match encoded " + password);
            return false;
        }
        return true;
    }
}
