package com.leveluplearning.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by daniel on 7/5/17.
 */

@Controller
public class HomeController {


    // With a ResponseBody annotation it will return the value produced by this method as response.
    @GetMapping("/")
    public String welcome() {
        return "index"; // it'll look for the view within templates if no ResponseBody annotation is found
    }
}
