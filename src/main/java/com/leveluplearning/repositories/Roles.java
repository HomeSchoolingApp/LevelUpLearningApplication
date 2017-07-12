package com.leveluplearning.repositories;

import com.leveluplearning.models.User;
import com.leveluplearning.models.UserRoles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by daniel on 7/6/17.
 */
public interface Roles extends CrudRepository<UserRoles, Long> {
    //using HQL
    @Query("select ur.role from UserRoles ur, User u where u.username=?1 and ur.userId = u.id")
    public List<String> ofUserWith(String username);
}
