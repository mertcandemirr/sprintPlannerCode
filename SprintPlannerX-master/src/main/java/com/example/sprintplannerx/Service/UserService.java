package com.example.sprintplannerx.Service;


import com.example.sprintplannerx.Entities.Task;
import com.example.sprintplannerx.Entities.User;
import com.example.sprintplannerx.Repository.TaskRepository;
import com.example.sprintplannerx.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, TaskRepository taskRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User getOneUser(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User updateOneUser(Integer userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        } else {
            return null;
        }
    }

    public User updateUserName(Integer userId, String name) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setName(name);
            userRepository.save(foundUser);
            return foundUser;
        } else {
            return null;
        }
    }

    public User updateEmail(Integer userId, String email) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setEmail(email);
            userRepository.save(foundUser);
            return foundUser;
        } else {
            return null;
        }
    }

    public User updateTracked(Integer userId, Long taskId) {
        Optional<User> user = userRepository.findById(userId);
        Task newTask = taskRepository.getTaskByID(taskId);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setOnTrackedTask(newTask);
            userRepository.save(foundUser);
            return foundUser;
        } else {
            return null;
        }
    }

    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }

    public void registerUser(String name, String username, String email, String password) throws Exception {

        if (userRepository.findByUsername(username).isEmpty()) {

            User user = new User();
            user.setUsername(username);
            user.setName(name);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));

            userRepository.save(user);
        } else {
            throw new Exception("there is already a user using that username");
        }
    }

    public Task getOnTrackTaskByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return user.getOnTrackedTask();
    }

    public void updateOnTrackTaskByID(Integer userId, Long taskId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with this ID: " + userId));

        Task newTask = taskRepository.getTaskByID(taskId);

        user.setOnTrackedTask(newTask);
        System.out.println("new task =" + user.getOnTrackedTask());
    }
}
