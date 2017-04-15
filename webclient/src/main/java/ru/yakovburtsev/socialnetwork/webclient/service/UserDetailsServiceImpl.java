/*
package ru.yakovburtsev.socialnetwork.webclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.yakovburtsev.socialnetwork.core.service.UserService;
import ru.yakovburtsev.socialnetwork.webclient.auth.AuthorizedUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return new AuthorizedUser(userService.getByEmail(email));
    }
}
*/
