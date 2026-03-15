package ru.kuzdikenov.controller;


import org.springframework.web.bind.annotation.RestController;
import ru.kuzdikenov.service.UserService;


@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
//    public User getUser(@RequestBody UserWithUsernameDto userWithUsernameDto) {
//        return userService.getUser(userWithUsernameDto);
//    }
//
//    @PostMapping("/user")
//    public void addUser(@RequestBody UserWithUsernameDto userWithUsernameDto) {
//        userService.addUser(userWithUsernameDto);
//    }
//
//    @DeleteMapping("/user")
//    public void deleteUser(@RequestBody UserWithUsernameDto userWithUsernameDto) {
//        userService.deleteUser(userWithUsernameDto);
//    }
//
//    @PutMapping("/user")
//    public void updateUser(@RequestBody UserWithIdAndUsernameDto userWithIdAndUsernameDto) {
//        userService.updateUser(userWithIdAndUsernameDto);
//    }
//
//
//    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<User> getUsers() {
//        return userService.findAll();
//    }

}
