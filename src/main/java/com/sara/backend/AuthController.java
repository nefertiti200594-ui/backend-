package com.sara.backend;
import com.sara.backend.dto.AuthResponse;
import com.sara.backend.dto.RegisterRequest;
import com.sara.backend.dto.LoginRequest;
import com.sara.backend.dto.UserResponse;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/auth")
public class AuthController {


    private final UserService userService;

    public AuthController(UserService userService) {

        this.userService = userService;

    }

    @PostMapping("/register")

    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {

        return userService.register(request);

    }

    @PostMapping("/login")

    public AuthResponse login(@Valid @RequestBody LoginRequest request) {

        return userService.login(request);

    }
    @GetMapping("/me")
    public UserResponse getMe(org.springframework.security.core.Authentication auth) {

        String email = auth.getName();

        return userService.getUserByEmail(email);
    }
    @GetMapping("/admin")
    public String adminPanel() {
        return "Admin panel";
    }
}
