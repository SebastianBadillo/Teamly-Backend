package com.chat.socket.ms_chat_socket.Dto;

import java.time.LocalDateTime;
import java.util.List;

public class CalendarEventRequest {
    public String title;
    public String description;
    public String location;
    public LocalDateTime startDateTime;
    public LocalDateTime endDateTime;
    public List<Long> participantIds;
}

