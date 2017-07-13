package com.leveluplearning.repositories;

import com.leveluplearning.models.Reference;
import com.leveluplearning.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daniel on 7/12/17.
 */

@Repository
public interface UsersReferences extends CrudRepository<Reference, Long> {
}
