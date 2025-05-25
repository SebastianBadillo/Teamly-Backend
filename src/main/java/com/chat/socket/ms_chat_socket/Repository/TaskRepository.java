package com.chat.socket.ms_chat_socket.Repository;

import com.chat.socket.ms_chat_socket.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

