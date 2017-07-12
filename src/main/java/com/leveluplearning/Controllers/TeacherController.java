package com.leveluplearning.Controllers;

import com.leveluplearning.models.User;
import com.leveluplearning.repositories.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by daniel on 7/7/17.
 */

@Controller
public class TeacherController {

    @Autowired
    TeacherRepo teacherDao;

    @GetMapping("/teachers")
    public String viewAll(Model model) {
        Iterable<User> users = teacherDao.findAllTeachers();
        model.addAttribute("teachers", users);
        return "views/viewAllTeacherProfiles";
    }

    @PostMapping("/teachers")
    public String searchTeacher(@RequestParam(name = "searchTerm") String searchTerm, Model model){
        List<User> teachers = teacherDao.findTeachersByName("%" + searchTerm + "%");
        model.addAttribute("teachers", teachers);
        return "views/viewAllTeacherProfiles";
    }

}
