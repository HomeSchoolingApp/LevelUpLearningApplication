package com.leveluplearning.repositories;

import com.leveluplearning.models.Subject;
import com.leveluplearning.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by violet on 7/11/17.
 */
@Repository
public interface SubjectsRepo extends CrudRepository<Subject, Long> {
    //
//    @Query ("Select p from Pet p inner join p.filtersPets f where f.id = :passID")
//    public ArrayList<Pet> findPetsByFilter(@Param("passID") long passID);

//    @Query (value = "select * from user_subject where subject_id = :subject_id", nativeQuery = true)
//    List<Subject> searchBySubject(@Param("subject_id") long subject_id);
}
