package com.sara.backend;

import com.sara.backend.dto.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.Collection;

@RestController
public class HelloController {

    private final UserService userService;

    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable String name) {
        return "Hello " + name + "!";
    }

    @GetMapping("/users")
    public UserPageResponse getUsers(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id,asc") String sort
    ) {
        return userService.getUsers(name, page, size, sort);
    }


    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public UserResponse updateUser(@PathVariable int id,
                                   @Valid @RequestBody UserRequest request) {
        return userService.updateUser(id, request);
    }

    @PostMapping("/users")
    public UserResponse addUser(@Valid @RequestBody UserRequest request) {
        return userService.addUser(request);
    }


    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
