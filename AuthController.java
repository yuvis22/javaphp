package com.example.hotelbluebird.controller;

import com.example.hotelbluebird.entity.User;
import com.example.hotelbluebird.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session, Model model) {
        User existingUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if (existingUser != null) {
            session.setAttribute("usermail", user.getEmail());
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "index";
        }
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, Model model) {
        if (userRepository.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email already exists");
        } else {
            userRepository.save(user);
            return "redirect:/home";
        }
        return "index";
    }
}
