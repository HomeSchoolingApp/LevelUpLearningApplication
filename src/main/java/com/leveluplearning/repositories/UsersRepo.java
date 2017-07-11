package com.leveluplearning.repositories;

import com.leveluplearning.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by daniel on 7/6/17.
 */

@Repository
public interface UsersRepo extends CrudRepository<User, Long> {
    @Query("select u from User u where u.profSum is not null")
    List<User> findTeachers();

    public User findByUsername (String username);
}