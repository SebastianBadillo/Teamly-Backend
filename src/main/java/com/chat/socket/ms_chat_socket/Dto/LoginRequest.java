package com.chat.socket.ms_chat_socket.Dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
