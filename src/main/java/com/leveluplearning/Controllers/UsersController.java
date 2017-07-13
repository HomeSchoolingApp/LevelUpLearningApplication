package com.leveluplearning.Controllers;

import com.leveluplearning.models.Reference;
import com.leveluplearning.models.User;
import com.leveluplearning.models.UserRoles;
import com.leveluplearning.models.UsersWithRoles;
import com.leveluplearning.repositories.*;
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

import javax.servlet.annotation.MultipartConfig;
import javax.validation.Valid;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
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
    UsersReferences referencesDao;

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

//        List<Reference> references = new ArrayList<>();
//
//        int i = 0;
//        for (String referencename:referencenames){
//            references.add(new Reference(editedUser ,referencename, referencephones.get(i),referencecomments.get(i)));
//
//            i++;
//        }
//
//        userCreated.setReferences(references);
//
//        usersDao.save(editedUser);

        return "redirect:/profile";
    }

    @PostMapping("/updatereferences/{id}")
    public String ProfileEditForm(@ModelAttribute User user, @PathVariable Long id, @RequestParam(name = "referencefullname") String referencenames,
                                  @RequestParam(name = "referencephone") String referencephones,
                                  @RequestParam(name = "referencecomment") String referencecomments) {


        User editedUser = usersDao.findOne(id);



//        editedUser.setReferences(references);

        referencesDao.save(new Reference(editedUser, referencenames, referencephones, referencecomments));

        return "redirect:/profile";
    }

    @GetMapping("/teachers")
    public String viewAllTeachers(Model model) {
        Iterable<User> users = teacherDao.findAllTeachers();
        model.addAttribute("teachers", users);
        return "views/viewAllTeacherProfiles";
    }

    @GetMapping("/terms")
    public String showTermsOfUse() {
        return "termsOfUse";
    }
}
