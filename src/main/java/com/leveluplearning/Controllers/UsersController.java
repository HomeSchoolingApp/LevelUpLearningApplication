//package com.leveluplearning.Controllers;
//
//import com.leveluplearning.repositories.UsersRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
///**
// * Created by daniel on 7/6/17.
// */
//
//@Controller
//public class UsersController {
//
//    @Autowired
//    UsersRepo usersDao;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping("/users/register")
//    public String saveUser(@ModelAttribute User user) {
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        usersDao.save(user);
//
//        return "redirect:/login";
//    }
//
//}
