package com.example.sprintplannerx.Controller;

import com.example.sprintplannerx.Entities.TaskComment;
import com.example.sprintplannerx.Entities.User;
import com.example.sprintplannerx.Service.TaskCommentService;
import com.example.sprintplannerx.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/task-comments")
public class TaskCommentController {

    private final TaskCommentService taskCommentService;
    private final UserService userService;

    @Autowired
    public TaskCommentController(TaskCommentService taskCommentService, UserService userService) {
        this.taskCommentService = taskCommentService;
        this.userService = userService;
    }

    @GetMapping
    public List<TaskComment> getAllTaskComments() {
        return taskCommentService.findAllComments();
    }

    @PostMapping
    public void addTaskComment(@RequestParam String text, @RequestParam Integer userId, @RequestParam Long taskId) throws Exception {
        User user = userService.getOneUser(userId);
        taskCommentService.saveComment(text, user, taskId);
    }

}
