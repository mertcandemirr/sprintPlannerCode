package com.example.sprintplannerx.Service;

import com.example.sprintplannerx.Entities.Task;
import com.example.sprintplannerx.Entities.TaskComment;
import com.example.sprintplannerx.Entities.User;
import com.example.sprintplannerx.Repository.TaskCommentRepository;
import com.example.sprintplannerx.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskCommentService {

    private final TaskCommentRepository taskCommentRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskCommentService(TaskCommentRepository taskCommentRepository, TaskRepository taskRepository) {
        this.taskCommentRepository = taskCommentRepository;
        this.taskRepository = taskRepository;
    }

    public List<TaskComment> findAllComments() {
        return taskCommentRepository.findAll();
    }

    public void saveComment(String text, User user, Long taskId) throws Exception {
        if (!text.isEmpty()) {
            TaskComment comment = new TaskComment();
            Task task = taskRepository.findById(taskId).orElse(null);

            comment.setText(text);
            comment.setUser(user);
            comment.setTask(task);

            taskCommentRepository.save(comment);
        } else {
            throw new Exception("Text field can not be empty!");
        }
    }
}
