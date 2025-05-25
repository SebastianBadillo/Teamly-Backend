package com.chat.socket.ms_chat_socket.Dto;

import java.time.LocalDateTime;
import java.util.List;

public class CalendarEventResponse {
    public Long id;
    public String title;
    public String description;
    public String location;
    public LocalDateTime startDateTime;
    public LocalDateTime endDateTime;
    public List<String> participantNames;
}
