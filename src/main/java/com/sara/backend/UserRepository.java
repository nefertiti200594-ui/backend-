package com.sara.backend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Page<User> findByNameContainingIgnoreCase(String name, PageRequest pageable);
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
}
