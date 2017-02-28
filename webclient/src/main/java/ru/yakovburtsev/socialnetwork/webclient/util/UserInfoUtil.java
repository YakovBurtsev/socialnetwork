package ru.yakovburtsev.socialnetwork.webclient.util;


import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoUtil {

    private UserInfoUtil() {}

    public static UserInfo toUserInfo(User user) {
        return new UserInfo(user.getId(), user.getName(), user.getSurname());
    }

    public static List<UserInfo> toUserInfoList(Collection<User> users) {
        return users
                .stream().map(UserInfoUtil::toUserInfo)
                .collect(Collectors.toList());
    }
}
