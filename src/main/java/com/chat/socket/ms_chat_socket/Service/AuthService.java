package com.chat.socket.ms_chat_socket.Service;

import com.chat.socket.ms_chat_socket.Dto.AuthResponse;
import com.chat.socket.ms_chat_socket.Dto.LoginRequest;
import com.chat.socket.ms_chat_socket.Dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    Boolean isEmailRegistered(String email);
}
