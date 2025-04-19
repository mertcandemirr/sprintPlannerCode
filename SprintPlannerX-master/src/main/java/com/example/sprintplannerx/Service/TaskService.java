package com.example.sprintplannerx.Service;


import com.example.sprintplannerx.Entities.Event;
import com.example.sprintplannerx.Entities.Task;
import com.example.sprintplannerx.Repository.EventRepository;
import com.example.sprintplannerx.Repository.TaskRepository;
import com.example.sprintplannerx.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;


    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> getUserTasksByStatus(String username, String status) {
        return taskRepository.findByUserAndStatus(username, status);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task createTask(Task newTask) {
        Task task = new Task();
        task.setName(newTask.getName());
        task.setStatus(newTask.getStatus());

        task.setDeveloper(userRepository.findByUsername(newTask.getDeveloper().getUsername()).orElse(null));
        task.setAnalyst(userRepository.findByUsername(newTask.getAnalyst().getUsername()).orElse(null));

        task.setFinalSP(newTask.getFinalSP());

        Event newEvent = eventRepository.findEventByEventName(newTask.getEvent().getEventName());
        task.setEvent(newEvent);
        List<Task> eventTasks = newEvent.getTasks();
        eventTasks.add(task);
        newEvent.setTasks(eventTasks);

        task.setDueDate(newTask.getDueDate());
        taskRepository.save(task);
        return task;
    }

    public Task updateOneTask(Long taskId, Task newTask) {
        Optional<Task> task = taskRepository.findById(taskId);

        if (task.isPresent()){
            Task foundTask = task.get();
            foundTask.setName(newTask.getName());
            foundTask.setStatus(newTask.getStatus());
            foundTask.setDeveloper(userRepository.findByUsername(newTask.getDeveloper().getUsername()).orElse(null));
            foundTask.setAnalyst(userRepository.findByUsername(newTask.getAnalyst().getUsername()).orElse(null));
            foundTask.setFinalSP(newTask.getFinalSP());
            foundTask.setIsStarred(newTask.isStarred());
            foundTask.setEvent(eventRepository.findEventByEventName(newTask.getEvent().getEventName()));
            foundTask.setDueDate(newTask.getDueDate());
            taskRepository.save(foundTask);
            return foundTask;
        }else{
            return  null;
        }
    }

    public Task updateTaskStatus(Long taskId, String newStatus) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            Task foundTask = task.get();
            foundTask.setStatus(newStatus);
            taskRepository.save(foundTask);
            return foundTask;
        } else {
            return null;
        }
    }

    public void deleteTaskById(Long taskId) {
        eventService.deleteEventsByTaskId(taskId);
        taskRepository.deleteById(taskId);
    }

    public List<Task> getStarredTasks(String username) {

        return taskRepository.getStarredTasksByUsername(username);


    }

    public List<Task> getTasksByUserName(String username) {

        List<Task> allTasks = taskRepository.findAll();
        return allTasks.stream()
                .filter(task -> task.getDeveloper().getUsername().equals(username) || task.getAnalyst().getUsername().equals(username) || task.getEvent().getLead().getUsername().equals(username))
                .collect(Collectors.toList());
    }
    public List<Task> getFavoriteTasks(String username) {
        List<Task> allTasks = taskRepository.findAll();
        return allTasks.stream()
                .filter(task -> task.getDeveloper().getUsername().equals(username) || task.getAnalyst().getUsername().equals(username) || task.getEvent().getLead().getUsername().equals(username))
                .filter(Task::isStarred)
                .collect(Collectors.toList());
    }


    public int getDoneTaskCountForUser(String username) {
        return taskRepository.getDoneTaskCountForUser(username);
    }

    public int getToDoTaskCountForUser(String username) {
        return taskRepository.getToDoTaskCountForUser(username);
    }

    public int getOverDueTaskCountForUser(String username) {
        return taskRepository.getOverdueTaskCountForUser(username);
    }

    public int getTotalTaskCountForUser(String username) {
        return getDoneTaskCountForUser(username)
                + getOverDueTaskCountForUser(username)
                + getToDoTaskCountForUser(username);
    }

    public String getPerformance(String username) {
        int doneTaskCount = getDoneTaskCountForUser(username);
        int totalTaskCount = getTotalTaskCountForUser(username);

        if (totalTaskCount == 0) {
            return "0%";
        }

        double performance = ((double) doneTaskCount / totalTaskCount) * 100;
        return String.format("%.2f%%", performance);
    }

    public List<Task> getTasksByDueDateASC(String username) {
        return taskRepository.findAllOrderByDueDate(username);

    }
}