package com.chat.socket.ms_chat_socket.Controller;

import com.chat.socket.ms_chat_socket.Dto.TaskRequest;
import com.chat.socket.ms_chat_socket.Dto.TaskResponse;
import com.chat.socket.ms_chat_socket.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:4200") // para pruebas sin CORS
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public TaskResponse createTask(@RequestBody TaskRequest request) {
        return taskService.createTask(request);
    }

    @GetMapping
    public List<TaskResponse> getTasks() {
        return taskService.getAllTasks();
    }
}
