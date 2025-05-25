package com.chat.socket.ms_chat_socket.Controller;

import com.chat.socket.ms_chat_socket.Dto.CalendarEventRequest;
import com.chat.socket.ms_chat_socket.Dto.CalendarEventResponse;
import com.chat.socket.ms_chat_socket.Service.CalendarEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendar")
@CrossOrigin(origins = "http://localhost:4200")
public class CalendarEventController {

    @Autowired
    private CalendarEventService calendarService;

    @PostMapping
    public CalendarEventResponse create(@RequestBody CalendarEventRequest request) {
        return calendarService.createEvent(request);
    }

    @GetMapping
    public List<CalendarEventResponse> getAll() {
        return calendarService.getAllEvents();
    }
}
