package com.chat.socket.ms_chat_socket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.socket.ms_chat_socket.Entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByName(String name);
    Boolean existsByName(String name);
    boolean existsById(Long id);
}
