package com.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import com.goit.domain.User;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String rootRedirect(HttpSession session) {
        if (session.getAttribute("currentUser") != null) {
            return "redirect:/note/list";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "loginForm";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              HttpSession session,
                              Model model) {
        User user = authService.authenticate(username, password);
        if (user != null) {
            session.setAttribute("currentUser", user);
            return "redirect:/note/list";
        } else {
            model.addAttribute("errorMessage", "Неправильне ім'я користувача або пароль.");
            return "loginForm";
        }
    }
}
