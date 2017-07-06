//package com.leveluplearning.repositories;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//
//import java.util.List;
//
///**
// * Created by daniel on 7/6/17.
// */
//public interface Roles extends CrudRepository<UserRole, Long> {
//
//    // Using HQL
//    @Query("select ur.role from UserRole ur, User u where u.username=?1 and ur.userId = u.id")
//    public List<String> ofUserWith(String username);
//}
