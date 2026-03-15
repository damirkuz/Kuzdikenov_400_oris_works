package ru.kuzdikenov.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kuzdikenov.model.Role;
import ru.kuzdikenov.model.User;
import ru.kuzdikenov.repository.RoleRepository;
import ru.kuzdikenov.repository.UserJpaRepository;
import ru.kuzdikenov.repository.UserRepository;
import ru.kuzdikenov.repository.UserRepositoryHiber;

import java.util.List;

@Service
public class UserService {

    private final UserRepositoryHiber userRepositoryHiber;
    private final UserRepository userRepository;

    private final UserJpaRepository userJpaRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepositoryHiber userRepositoryHiber, UserRepository userRepository, UserJpaRepository userJpaRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepositoryHiber = userRepositoryHiber;
        this.userRepository = userRepository;
        this.userJpaRepository = userJpaRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
//
//    @Transactional
//    public void addUser(UserWithUsernameDto userWithUsernameDto) {
//        User user = User.builder()
//                .username(userWithUsernameDto.username())
//                .build();
//        userRepository.save(user);
//    }
//
//    @Transactional(readOnly = true)
//    public List<User> findAll() {
//        return userRepository.findAll();

    /// /        return userRepositoryHiber.findAll();
//    }
//
//    @Transactional
//    public User getUser(UserWithUsernameDto userWithUsernameDto) {
//        return userRepository.findByUsername(userWithUsernameDto.username()).get();
//    }
//
//    @Transactional
//    public void deleteUser(UserWithUsernameDto userWithUsernameDto) {
//        userRepository.deleteByUsername(userWithUsernameDto.username());
//    }
//
//    @Transactional
//    public void updateUser(UserWithIdAndUsernameDto userWithIdAndUsernameDto) {
//        Optional<User> user = userRepository.findById(userWithIdAndUsernameDto.id());
//        if (user.isPresent()) {
//            User user1 = user.get();
//            user1.setUsername(userWithIdAndUsernameDto.username());
//            userRepository.save(user1);
//        }
//    }

    @Transactional
    public void registerNewUser(String username, String rawPassword) {
        if (userJpaRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Пользователь с таким именем уже существует");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Роль USER не найдена в БД"));

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(rawPassword))
                .roles(List.of(userRole))
                .build();

        userJpaRepository.save(user);
    }
}
