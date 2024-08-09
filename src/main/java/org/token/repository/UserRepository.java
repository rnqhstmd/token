package org.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.token.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UUID, User> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
}
