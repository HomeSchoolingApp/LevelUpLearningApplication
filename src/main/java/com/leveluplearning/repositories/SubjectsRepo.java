package com.leveluplearning.repositories;

import com.leveluplearning.models.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by violet on 7/11/17.
 */
@Repository
public interface SubjectsRepo extends CrudRepository<Subject, Long> {


}
