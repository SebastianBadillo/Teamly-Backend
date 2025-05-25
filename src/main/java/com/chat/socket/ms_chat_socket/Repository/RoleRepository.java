package com.chat.socket.ms_chat_socket.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.socket.ms_chat_socket.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}