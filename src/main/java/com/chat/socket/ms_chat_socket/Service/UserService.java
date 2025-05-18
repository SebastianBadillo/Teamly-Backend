package com.chat.socket.ms_chat_socket.Service;

import com.chat.socket.ms_chat_socket.Dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
}
