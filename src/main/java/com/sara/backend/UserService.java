package com.sara.backend;

import com.sara.backend.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.sara.backend.dto.AuthResponse;
import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;

    }

    public UserPageResponse getUsers(String name, int page, int size, String sort) {
        String[] sortParts = sort.split(",");
        String sortField = sortParts[0];

        String sortDirection = sortParts.length > 1 ? sortParts[1] : "asc";

        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc")

                ? Sort.Direction.DESC

                : Sort.Direction.ASC;


        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortField));
        Page<User> usersPage = (name != null && !name.isBlank())

                ? userRepository.findByNameContainingIgnoreCase(name, pageRequest)

                : userRepository.findAll(pageRequest);

        List<UserResponse> content = usersPage
                .stream()
                .map(this::mapToResponse)
                .toList();

        return new UserPageResponse(
                content,
                usersPage.getNumber(),
                usersPage.getSize(),
                usersPage.getTotalElements(),
                usersPage.getTotalPages()
        );
    }

    public UserResponse getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User tapılmadı"));

        return mapToResponse(user);
    }

    public UserResponse addUser(UserRequest request) {
        String cleanName = request.getName().trim();

        User user = new User();
        user.setName(cleanName);
        user.setAge(request.getAge());
        user.setEmail(request.getEmail());
        user.setPassword("1234");

        User saved = userRepository.save(user);

        return mapToResponse(saved);
    }

    public UserResponse updateUser(int id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User tapılmadı"));

        user.setName(request.getName());
        user.setAge(request.getAge());
        user.setEmail(request.getEmail());

        User updated = userRepository.save(user);

        return mapToResponse(updated);
    }

    public String deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User tapılmadı");
        }

        userRepository.deleteById(id);
        return "User silindi";
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getAge(),
                user.getEmail(),
                user.getRole()

        );
    }

    public AuthResponse register(RegisterRequest request) {

        userRepository.findByEmail(request.getEmail())
                .ifPresent(u -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bu email artıq mövcuddur");
                });

        User user = new User();
        user.setName(request.getName().trim());
        user.setAge(request.getAge());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        User saved = userRepository.save(user);
        String token = jwtService.generateToken(user);

        return new AuthResponse(token);

    }


    public AuthResponse login(LoginRequest request) {
        System.out.println("LOGIN CALLED");

        User user = userRepository.findByEmail(request.getEmail())

                .orElseThrow(() -> {

                    System.out.println("USER NOT FOUND");

                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "User tapılmadı");

                });

        System.out.println("USER FOUND: " + user.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {

            System.out.println("PASSWORD WRONG");

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password səhvdir");

        }

        System.out.println("PASSWORD OK");

        String token = jwtService.generateToken(user);

        System.out.println("TOKEN GENERATED: " + token);

        return new AuthResponse(token);

    }
    public String extractEmailFromToken(String token) {
        return jwtService.extractEmail(token);
    }

    public UserResponse getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User tapılmadı"));

        return mapToResponse(user);
    }
    public Page<UserResponse> getAllUsers(int page, int size) {

        return userRepository

                .findAll(PageRequest.of(page, size))

                .map(this::mapToResponse);

    }
    public User findUserEntityById(int id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User tapılmadı"
                        ));
    }

    public void save(User user) {
        userRepository.save(user);
    }
}

