package ru.yakovburtsev.socialnetwork.user.util;


import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class UserInfoUtil {
    public static List<UserInfo> toUserInfoList(Collection<User> users) {
        return users
                .stream().map(UserInfoUtil::toUserInfo)
                .collect(toList());
    }
    private static UserInfo toUserInfo(User user) {
        return new UserInfo(user.getId(), user.getName(), user.getSurname());
    }
}
