package com.chat.socket.ms_chat_socket.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthResponse {
    private String message;
    private String email;
    private String name;
    private Long id;
}
