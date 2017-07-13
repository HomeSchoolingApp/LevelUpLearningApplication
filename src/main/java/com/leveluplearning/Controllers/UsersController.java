package com.leveluplearning.Controllers;

import com.leveluplearning.models.*;
import com.leveluplearning.repositories.*;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.annotation.MultipartConfig;
import javax.validation.Valid;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
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
  
    @PostMapping("/updatesubjects/{id}")
    public String ProfileEditForm(@RequestParam(name="subjects") long[] subjectsIds, @PathVariable long id) {
        User teacher = usersDao.findOne(id);

        System.out.println(Arrays.toString(subjectsIds));
        List<Subject> subjects = new ArrayList<>();
        for (int i = 0; i < subjectsIds.length; i++) {
            /*Subject subject = new Subject();
            subject.setId(subjectsIds[i]);*/
            Subject subject = subjectsDao.findOne(subjectsIds[i]);
            subjects.add(subject);
        }

        teacher.setSubjects(subjects);

        usersDao.save(teacher);

        /*User editedUser = usersDao.findOne(id);



//        editedUser.setReferences(references);

        referencesDao.save(new Reference(editedUser, referencenames, referencephones));*/

        return "redirect:/profile";
    }

    @GetMapping("/terms")
    public String showTermsOfUse() {
        return "termsOfUse";
    }
}
