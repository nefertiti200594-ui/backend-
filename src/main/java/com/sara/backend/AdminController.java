package com.sara.backend;

import com.sara.backend.dto.UserPageResponse;
import com.sara.backend.dto.UserResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public UserPageResponse getUsers(

            @RequestParam(required = false) String name,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "id,asc") String sort
    ) {

        return userService.getUsers(

                name,

                page,

                size,

                sort
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/make-admin/{id}")
    public String makeAdmin(@PathVariable int id) {

        User user = userService.findUserEntityById(id);

        user.setRole(Role.ADMIN);

        userService.save(user);

        return "User artıq ADMIN oldu";
    }
}