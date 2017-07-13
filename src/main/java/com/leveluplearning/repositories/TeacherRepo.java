package com.leveluplearning.repositories;

import com.leveluplearning.models.Subject;
import com.leveluplearning.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 7/7/17.
 */

@Repository
public interface TeacherRepo extends CrudRepository<User, Long> {
    // Using HQL
    @Query("select u from User u where u.profSum is not null")
    public List<User> findAllTeachers();

    List<User> findAllById(List<Long> ids);

    @Query(value = "Select u from User u inner join u.user_subject s where s.subject_id = :subject_id", nativeQuery = true)
    List<User> findAllBySubjects(@Param("subject_id") long subject_id);


    @Query("select u from User u where u.firstName like ?1 or u.lastName like ?1 and u.profSum is not null")
    List<User> findTeachersByName(String searchTerm);

}
