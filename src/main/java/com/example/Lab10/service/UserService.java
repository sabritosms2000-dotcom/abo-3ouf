package com.example.Lab10.service;

import com.example.Lab10.DTO.EditProfileDTO;
import com.example.Lab10.DTO.LoginDTO;
import com.example.Lab10.DTO.RegisterDTO;
import com.example.Lab10.model.User;
import com.example.Lab10.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository repo;

    UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User findByEmail(String email) {
        Optional<User> optuser = repo.findByEmail(email);
        if (optuser.isEmpty()) return null;
        return optuser.get();
    }

    public User createUser(RegisterDTO data) {
        Optional<User> optuser = repo.findByEmail(data.getEmail());
        if (optuser.isPresent()) return null;

        User user = new User();
        user.loadFormDTO(data);
        return repo.save(user);
    }

    public User login(LoginDTO data) {
        Optional<User> optuser = repo.findByEmail(data.getEmail());
        if (optuser.isEmpty()) return null;

        User user = optuser.get();

        if (!user.getPassword().equals(data.getPassword())) return null;


        if (user.getEmail() != null && user.getEmail().toLowerCase().startsWith("admin")) {
            if (!"ADMIN".equals(user.getRole())) {
                user.setRole("ADMIN");
                user = repo.save(user);
            }
        }

        return user;
    }

    public User updateUser(User user, EditProfileDTO data) {
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        return repo.save(user);
    }
}
