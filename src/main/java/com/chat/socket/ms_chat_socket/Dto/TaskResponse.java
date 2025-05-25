package com.chat.socket.ms_chat_socket.Dto;

import java.time.LocalDateTime;

public class TaskResponse {
    public Long id;
    public String name;
    public String description;
    public String priority;
    public LocalDateTime deadline;
    public boolean completed;
    public String assignedUserName;
}

