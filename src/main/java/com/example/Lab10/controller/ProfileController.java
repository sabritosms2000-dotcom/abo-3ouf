

package com.example.Lab10.controller;

import com.example.Lab10.DTO.EditProfileDTO;
import com.example.Lab10.model.User;
import com.example.Lab10.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/profiles"})
public class ProfileController {
    private UserService userService;

    ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/view"})
    String viewProfilePage(HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("user", user);
            return "profile";
        }
    }

    @PostMapping({"/update"})
    String updateUser(HttpSession session, @ModelAttribute EditProfileDTO data, Model model) {
        User user = (User)session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        } else {
            user = this.userService.updateUser(user, data);
            session.setAttribute("user", user);
            model.addAttribute("success", "Updated Successfully");
            model.addAttribute("user", user);
            return "profile";
        }
    }
}
