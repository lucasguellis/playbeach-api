package com.pbio.playbeach.repositories;

import com.pbio.playbeach.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<com.pbio.playbeach.entities.User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findByNameContainingIgnoreCase(String name);
}
