package com.leveluplearning.Controllers;

import com.leveluplearning.models.Subject;
import com.leveluplearning.models.User;
import com.leveluplearning.repositories.SubjectsRepo;
import com.leveluplearning.repositories.TeacherRepo;
import com.leveluplearning.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by violet on 7/12/17.
 */
@Controller
public class SubjectsController {

    @Autowired
    SubjectsRepo subjectsDao;

    @Autowired
    TeacherRepo teacherDao;

    @Autowired
    UsersRepo usersDao;

    @GetMapping("/subjects")
    public String getSubjects() {
        subjectsDao.findAll();
        return "";
    }

    @PostMapping("/searchTeachersBySubjects")
    public String searchTeachersBySubjects(Model model, @RequestParam(name = "SearchSubjects", required = false) List<Subject> selectedSubjects) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.findByUsername(loggedInUser.getUsername());
        model.addAttribute("user", user);

        List<User> teachers = new ArrayList<>();

//        if (selectedSubjects.isEmpty()){
//            return "redirect:/teachers";
//        }

        for (Subject subject : selectedSubjects) {
            if (selectedSubjects.isEmpty()) {
                return "redirect:/teachers";
            }
            for (User teacher : subject.getTeachers()) {
                if (teachers.indexOf(teacher) == -1) {
                    teachers.add(teacher);
                }
            }
        }

        model.addAttribute("teachers", teachers);
        return "views/viewAllTeacherProfiles";

    }

}
