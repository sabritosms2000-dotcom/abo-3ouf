//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.Lab10.controller;

import com.example.Lab10.DTO.LoginDTO;
import com.example.Lab10.DTO.RegisterDTO;
import com.example.Lab10.model.User;
import com.example.Lab10.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
	private UserService userService;

	MainController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping({"/login"})
	String viewLoginForm() {
		return "login";
	}

	@PostMapping({"/login"})
	String loginUser(@ModelAttribute LoginDTO data, Model model, HttpSession session) {
		User user = this.userService.login(data);
		if (user == null) {
			model.addAttribute("error", "Invalid email or password");
			return "login";
		} else {
			session.setAttribute("user", user);
			return "redirect:/profiles/view";
		}
	}

	@GetMapping({"/register"})
	String viewRegisterForm() {
		return "register";
	}

	@PostMapping({"/register"})
	String registerUser(@ModelAttribute RegisterDTO data, Model model, HttpSession session) {
		User user = this.userService.createUser(data);
		if (user == null) {
			model.addAttribute("error", "Email already in use");
			return "register";
		} else {
			session.setAttribute("user", user);
			return "redirect:/profiles/view";
		}
	}

	@GetMapping({"/logout"})
	String logoutUser(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
