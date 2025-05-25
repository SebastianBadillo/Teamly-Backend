package com.chat.socket.ms_chat_socket.Service;

import com.chat.socket.ms_chat_socket.Dto.TaskRequest;
import com.chat.socket.ms_chat_socket.Dto.TaskResponse;

import java.util.List;

public interface TaskService {
    TaskResponse createTask(TaskRequest request);
    List<TaskResponse> getAllTasks();
    void createTeam(String name, String description);
}
