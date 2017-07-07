package com.leveluplearning.Controllers;

import com.leveluplearning.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by daniel on 7/6/17.
 */

@Controller
public class AuthenticationController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(
            Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
}
