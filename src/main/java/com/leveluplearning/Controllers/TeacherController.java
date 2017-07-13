package com.leveluplearning.Controllers;

import com.leveluplearning.models.User;
import com.leveluplearning.repositories.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/searchTeachersBySunjects")
    public String searchBySubject(Model model){
        Iterable<User> users = teacherDao.findAllTeachers();
        model.addAttribute("teachers", users);
        return "redirect:/teachers";
    }

}
