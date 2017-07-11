package com.leveluplearning.repositories;

import com.leveluplearning.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by daniel on 7/7/17.
 */
@Repository
public interface TeacherRepo extends CrudRepository<User, Long> {
    // Using HQL
    @Query("select u from User u where u.profSum is not null")
    public List<User> findAllTeachers();
}
