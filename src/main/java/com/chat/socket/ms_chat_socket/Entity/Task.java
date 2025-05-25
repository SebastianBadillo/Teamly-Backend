package com.chat.socket.ms_chat_socket.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String priority;
    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    private User assignedUser;

    private boolean completed = false;

    // Getters y Setters
}
