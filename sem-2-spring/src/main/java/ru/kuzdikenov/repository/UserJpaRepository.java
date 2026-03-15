package ru.kuzdikenov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kuzdikenov.model.User;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
