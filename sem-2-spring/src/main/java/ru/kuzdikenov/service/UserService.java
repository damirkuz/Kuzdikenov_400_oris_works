package ru.kuzdikenov.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kuzdikenov.dto.UserCreateDto;
import ru.kuzdikenov.model.User;
import ru.kuzdikenov.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void addUser(UserCreateDto userDto) {
        User user = new User();
        user.setUsername(userDto.username());
        userRepository.add(user);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
