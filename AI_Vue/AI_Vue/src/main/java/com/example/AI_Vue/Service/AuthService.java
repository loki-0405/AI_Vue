package com.example.AI_Vue.Service;

import com.example.AI_Vue.Models.Role;
import org.springframework.stereotype.Service;
import com.example.AI_Vue.Dto.RegisterRequest;
import com.example.AI_Vue.Models.User;
import com.example.AI_Vue.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

        @Autowired
        private UserRepo userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        public void register(RegisterRequest request) {
            User user = new User();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setRole(Role.USER);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            if (userRepository.existsByUsername(user.getUsername())) {
                throw new RuntimeException("User already exists with username: " + user.getUsername());
            }
            userRepository.save(user);
        }
}
