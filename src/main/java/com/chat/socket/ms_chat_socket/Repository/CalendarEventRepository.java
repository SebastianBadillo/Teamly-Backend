package com.chat.socket.ms_chat_socket.Repository;

import com.chat.socket.ms_chat_socket.Entity.CalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Long> {
}
