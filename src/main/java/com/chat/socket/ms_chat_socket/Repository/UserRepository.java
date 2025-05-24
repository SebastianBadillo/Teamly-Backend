package com.chat.socket.ms_chat_socket.Repository;

import com.chat.socket.ms_chat_socket.Entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

}
