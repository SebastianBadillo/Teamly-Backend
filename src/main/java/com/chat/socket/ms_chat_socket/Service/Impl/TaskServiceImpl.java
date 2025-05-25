package com.chat.socket.ms_chat_socket.Service;

import com.chat.socket.ms_chat_socket.Dto.TaskRequest;
import com.chat.socket.ms_chat_socket.Dto.TaskResponse;
import com.chat.socket.ms_chat_socket.Entity.Task;
import com.chat.socket.ms_chat_socket.Entity.User;
import com.chat.socket.ms_chat_socket.Repository.TaskRepository;
import com.chat.socket.ms_chat_socket.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public TaskResponse createTask(TaskRequest request) {
        Task task = new Task();
        task.setName(request.name);
        task.setDescription(request.description);
        task.setPriority(request.priority);
        task.setDeadline(request.deadline);

        User user = userRepo.findById(request.assignedUserId).orElse(null);
        task.setAssignedUser(user);

        taskRepo.save(task);

        TaskResponse response = new TaskResponse();
        response.id = task.getId();
        response.name = task.getName();
        response.description = task.getDescription();
        response.priority = task.getPriority();
        response.deadline = task.getDeadline();
        response.completed = task.isCompleted();
        response.assignedUserName = user != null ? user.getFirstName() + " " + user.getLastName() : null;

        return response;
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepo.findAll().stream().map(task -> {
            TaskResponse res = new TaskResponse();
            res.id = task.getId();
            res.name = task.getName();
            res.description = task.getDescription();
            res.priority = task.getPriority();
            res.deadline = task.getDeadline();
            res.completed = task.isCompleted();
            res.assignedUserName = task.getAssignedUser() != null ?
                task.getAssignedUser().getFirstName() + " " + task.getAssignedUser().getLastName() : null;
            return res;
        }).collect(Collectors.toList());
    }
}
