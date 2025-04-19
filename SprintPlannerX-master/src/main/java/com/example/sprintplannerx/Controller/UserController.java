package com.example.sprintplannerx.Controller;


import com.example.sprintplannerx.Entities.User;
import com.example.sprintplannerx.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }


    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Integer userId) {
        return userService.getOneUser(userId);
    }

    @PutMapping(value = "/{userId}", consumes = "application/json", produces = "application/json")
    public User updateOneUser(@PathVariable String userId,
                              @RequestBody User newUser) {
        Integer userIdInt = Integer.parseInt(userId);
        return userService.updateOneUser(userIdInt, newUser);
    }

    @PutMapping(value = "/updateName/{userId}", consumes = "application/json", produces = "application/json")
    public User updateUsername(@PathVariable Integer userId, @RequestBody String newName) {
        return userService.updateUserName(userId, newName);
    }

    @PutMapping(value = "/updateEmail/{userId}", consumes = "application/json", produces = "application/json")
    public User updateEmail(@PathVariable Integer userId, @RequestBody String Email) {
        return userService.updateEmail(userId, Email);
    }

    @PutMapping(value = "/updateTracked/{userId}", consumes = "application/json", produces = "application/json")
    public User updateTracked(@PathVariable Integer userId, @RequestBody String taskId) {
        Long longTaskId = Long.parseLong(taskId);
        return userService.updateTracked(userId, longTaskId);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Integer userId) {
        userService.deleteUserById(userId);
    }

    @PutMapping("/{userId}/{taskId}")
    public void updateOnTrackedTask(@PathVariable Integer userId, @PathVariable Long taskId) {
        userService.updateOnTrackTaskByID(userId, taskId);
    }

}
