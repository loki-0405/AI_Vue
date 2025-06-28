package com.example.AI_Vue.Controller;

import com.example.AI_Vue.Dto.JwtResponse;
import com.example.AI_Vue.Dto.LoginRequest;
import com.example.AI_Vue.Dto.RegisterRequest;
import com.example.AI_Vue.Security.JwtUtil;
import com.example.AI_Vue.Service.AuthService;
import com.example.AI_Vue.Security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
       try {
           authService.register(request);
           return ResponseEntity.ok("User Registered");
       }catch (Exception e){
           System.out.println(e);
           return ResponseEntity.badRequest().body("failed");
       }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}