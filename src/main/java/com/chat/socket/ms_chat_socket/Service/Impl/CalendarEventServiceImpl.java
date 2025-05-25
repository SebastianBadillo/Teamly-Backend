package com.chat.socket.ms_chat_socket.Service;

import com.chat.socket.ms_chat_socket.Dto.CalendarEventRequest;
import com.chat.socket.ms_chat_socket.Dto.CalendarEventResponse;
import com.chat.socket.ms_chat_socket.Entity.CalendarEvent;
import com.chat.socket.ms_chat_socket.Entity.User;
import com.chat.socket.ms_chat_socket.Repository.CalendarEventRepository;
import com.chat.socket.ms_chat_socket.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarEventServiceImpl implements CalendarEventService {

    @Autowired
    private CalendarEventRepository eventRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public CalendarEventResponse createEvent(CalendarEventRequest request) {
        CalendarEvent event = new CalendarEvent();
        event.setTitle(request.title);
        event.setDescription(request.description);
        event.setLocation(request.location);
        event.setStartDateTime(request.startDateTime);
        event.setEndDateTime(request.endDateTime);

        List<User> users = userRepo.findAllById(request.participantIds);
        event.setParticipants(users);

        eventRepo.save(event);

        CalendarEventResponse response = new CalendarEventResponse();
        response.id = event.getId();
        response.title = event.getTitle();
        response.description = event.getDescription();
        response.location = event.getLocation();
        response.startDateTime = event.getStartDateTime();
        response.endDateTime = event.getEndDateTime();
        response.participantNames = users.stream()
                .map(u -> u.getFirstName() + " " + u.getLastName())
                .collect(Collectors.toList());

        return response;
    }

    @Override
    public List<CalendarEventResponse> getAllEvents() {
        return eventRepo.findAll().stream().map(event -> {
            CalendarEventResponse res = new CalendarEventResponse();
            res.id = event.getId();
            res.title = event.getTitle();
            res.description = event.getDescription();
            res.location = event.getLocation();
            res.startDateTime = event.getStartDateTime();
            res.endDateTime = event.getEndDateTime();
            res.participantNames = event.getParticipants().stream()
                    .map(u -> u.getFirstName() + " " + u.getLastName())
                    .collect(Collectors.toList());
            return res;
        }).collect(Collectors.toList());
    }
}

