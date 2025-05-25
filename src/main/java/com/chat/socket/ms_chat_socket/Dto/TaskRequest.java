package com.chat.socket.ms_chat_socket.Dto;

import java.time.LocalDateTime;

public class TaskRequest {
    public String name;
    public String description;
    public String priority;
    public LocalDateTime deadline;
    public Long assignedUserId;
}

