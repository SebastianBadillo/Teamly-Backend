package com.chat.socket.ms_chat_socket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.socket.ms_chat_socket.Entity.Files;

public interface FilesRepository extends JpaRepository<Files, Long> {
    
}
