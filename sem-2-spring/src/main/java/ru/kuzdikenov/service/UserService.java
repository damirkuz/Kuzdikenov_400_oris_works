package ru.kuzdikenov.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kuzdikenov.dto.UserWithIdAndUsernameDto;
import ru.kuzdikenov.dto.UserWithUsernameDto;
import ru.kuzdikenov.model.User;
import ru.kuzdikenov.repository.UserRepository;
import ru.kuzdikenov.repository.UserRepositoryHiber;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepositoryHiber userRepositoryHiber;
    private final UserRepository userRepository;

    public UserService(UserRepositoryHiber userRepositoryHiber, UserRepository userRepository) {
        this.userRepositoryHiber = userRepositoryHiber;
        this.userRepository = userRepository;
    }

    @Transactional
    public void addUser(UserWithUsernameDto userWithUsernameDto) {
        User user = new User();
        user.setUsername(userWithUsernameDto.username());
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
//        return userRepositoryHiber.findAll();
    }

    @Transactional
    public User getUser(UserWithUsernameDto userWithUsernameDto) {
        return userRepository.findByUsername(userWithUsernameDto.username()).get();
    }

    @Transactional
    public void deleteUser(UserWithUsernameDto userWithUsernameDto) {
        userRepository.deleteByUsername(userWithUsernameDto.username());
    }

    @Transactional
    public void updateUser(UserWithIdAndUsernameDto userWithIdAndUsernameDto) {
        Optional<User> user = userRepository.findById(userWithIdAndUsernameDto.id());
        if (user.isPresent()) {
            User user1 = user.get();
            user1.setUsername(userWithIdAndUsernameDto.username());
            userRepository.save(user1);
        }
    }
}
