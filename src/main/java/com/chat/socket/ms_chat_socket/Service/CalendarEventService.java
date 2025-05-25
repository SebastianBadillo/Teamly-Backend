package com.chat.socket.ms_chat_socket.Service;

import com.chat.socket.ms_chat_socket.Dto.CalendarEventRequest;
import com.chat.socket.ms_chat_socket.Dto.CalendarEventResponse;

import java.util.List;

public interface CalendarEventService {
    CalendarEventResponse createEvent(CalendarEventRequest request);
    List<CalendarEventResponse> getAllEvents();
}
