package com.leveluplearning.Controllers;

import com.leveluplearning.models.Subject;
import com.leveluplearning.models.User;
import com.leveluplearning.repositories.SubjectsRepo;
import com.leveluplearning.repositories.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/subjects")
    public String getSubjects(){
        subjectsDao.findAll();
        return "";
    }

    @PostMapping("/searchTeachersBySubjects")
    public String searchTeachersBySubjects(Model model){
        List<User> teachersBySubject = teacherDao.findAllBySubjects(1L);
        model.addAttribute("teachers", teachersBySubject);
        return "views/viewAllTeacherProfiles";
    }

}
