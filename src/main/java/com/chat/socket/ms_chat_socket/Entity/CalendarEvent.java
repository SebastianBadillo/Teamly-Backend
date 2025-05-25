package com.chat.socket.ms_chat_socket.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "calendar_events")
@Data
public class CalendarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    @ManyToMany
    @JoinTable(
        name = "calendar_event_participants",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participants;

    // Getters y Setters
}
