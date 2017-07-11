package com.leveluplearning.Controllers;

import com.leveluplearning.models.User;
import com.leveluplearning.models.UserRoles;
import com.leveluplearning.models.UsersWithRoles;
import com.leveluplearning.repositories.Roles;
import com.leveluplearning.repositories.TeacherRepo;
import com.leveluplearning.repositories.UsersRepo;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.sql.Connection;
import java.util.logging.Logger;

/**
 * Created by daniel on 7/6/17.
 */

@Controller
public class UsersController {

    @Autowired
    UsersRepo usersDao;

    @Autowired
    Roles rolesDao;

    @Autowired
    TeacherRepo teacherDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/users/register")
    public String saveUser(@ModelAttribute User user,
                           @RequestParam (name = "role") String role) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));


        User registered = usersDao.save(user);

        registered.getId();

        UserRoles userRole = new UserRoles(role, registered.getId());
        rolesDao.save(userRole);

        return "redirect:/login";
    }


    @GetMapping("/profile")
    public String showParentProfile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "users/profile";
    }

    @GetMapping("/teachers")
    public String viewAll(Model model) {
        Iterable<User> users = teacherDao.findAllTeachers();
        model.addAttribute("teachers", users);
        return "views/viewAllTeacherProfiles";
    }

    @GetMapping("/terms")
    public String showTermsOfUse() {
        return "termsOfUse";
    }
}
