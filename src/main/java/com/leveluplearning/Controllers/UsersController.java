package com.leveluplearning.Controllers;

import com.leveluplearning.models.User;
import com.leveluplearning.models.UserRoles;
import com.leveluplearning.models.UsersWithRoles;
import com.leveluplearning.repositories.Roles;
import com.leveluplearning.repositories.SubjectsRepo;
import com.leveluplearning.repositories.TeacherRepo;
import com.leveluplearning.repositories.UsersRepo;
import com.leveluplearning.models.*;
import com.leveluplearning.repositories.*;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    @Value("${file-upload-path}")
    private String uploadPath;

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
    public String ProfileEditForm(@ModelAttribute User user, @PathVariable Long id, @RequestParam(name = "file") MultipartFile uploadedFile,
                                  Model model) {

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

        String filename = uploadedFile.getOriginalFilename();
        String filepath = Paths.get(uploadPath, filename).toString();
        File destinationFile = new File(filepath);
        try {
            uploadedFile.transferTo(destinationFile);
            model.addAttribute("message", "File successfully uploaded!");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Oops! Something went wrong! " + e);
        }

        editedUser.setImgUrl(filename);
        usersDao.save(editedUser);
        model.addAttribute("user", editedUser);

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
            Subject subject = subjectsDao.findOne(subjectsIds[i]);
            subjects.add(subject);
        }

        teacher.setSubjects(subjects);

        usersDao.save(teacher);

        return "redirect:/profile";
    }

    @GetMapping("/terms")
    public String showTermsOfUse() {
        return "termsOfUse";
    }
}
