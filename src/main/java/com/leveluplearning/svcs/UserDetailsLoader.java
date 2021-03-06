package com.leveluplearning.svcs;

import com.leveluplearning.models.User;
import com.leveluplearning.models.UsersWithRoles;
import com.leveluplearning.repositories.Roles;
import com.leveluplearning.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by daniel on 6/22/17.
 */

@Service("customUserDetailsService")
public class UserDetailsLoader implements UserDetailsService {

    private final UsersRepo users;
    private final Roles roles;

    @Autowired
    public UserDetailsLoader(UsersRepo users, Roles roles) {
        this.users = users;
        this.roles = roles;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }

        List<String> userRoles = roles.ofUserWith(username);
        return  new UsersWithRoles(user, userRoles);
    }
}