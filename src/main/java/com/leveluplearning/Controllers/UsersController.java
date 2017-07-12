package com.leveluplearning.Controllers;

import com.leveluplearning.models.User;
import com.leveluplearning.models.UserRoles;
import com.leveluplearning.models.UsersWithRoles;
import com.leveluplearning.repositories.Roles;
import com.leveluplearning.repositories.SubjectsRepo;
import com.leveluplearning.repositories.TeacherRepo;
import com.leveluplearning.repositories.UsersRepo;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.annotation.MultipartConfig;
import java.nio.file.Files;
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
    SubjectsRepo subjectsDao;

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
    public String showProfile(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.findByUsername(loggedInUser.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("subjects", subjectsDao.findAll());
        return "users/index";
    }


    @GetMapping("/updateprofile")
    public String showProfileEditForm(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.findByUsername(loggedInUser.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("subjects", subjectsDao.findAll());
        return "users/profile";
    }
  
    @PostMapping("/updateprofile/{id}")
    public String ProfileEditForm(@ModelAttribute User user, @PathVariable Long id) {
        User editedUser = usersDao.findOne(id);
        editedUser.setAboutMe(user.getAboutMe());
        editedUser.setCertification(user.getCertification());
        editedUser.setCity(user.getCity());
        editedUser.setDob(user.getDob());
        editedUser.setEduLevel(user.getEduLevel());
        editedUser.setEmail(user.getEmail());
        editedUser.setFirstName(user.getFirstName());
        editedUser.setLastName(user.getLastName());
        editedUser.setGender(user.getGender());
        editedUser.setLanguage(user.getLanguage());
        editedUser.setPhoneNumber(user.getPhoneNumber());
        editedUser.setProfSum(user.getProfSum());
        editedUser.setReferences(user.getReferences());
        editedUser.setState(user.getState());
        editedUser.setSubjects(user.getSubjects());

        usersDao.save(editedUser);

        return "redirect:/profile";
    }

    @GetMapping("/terms")
    public String showTermsOfUse() {
        return "termsOfUse";
    }
}
