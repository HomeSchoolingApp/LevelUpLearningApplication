package com.leveluplearning.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by daniel on 7/7/17.
 */

@Controller
public class ParentController {

    @GetMapping("/parent")
    public String showParentProfile() {
        return "profiles/parent";
    }

}
