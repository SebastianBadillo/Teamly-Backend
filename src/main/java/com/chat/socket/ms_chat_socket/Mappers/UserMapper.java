package com.chat.socket.ms_chat_socket.Mappers;

import com.chat.socket.ms_chat_socket.Dto.UserDto;
import com.chat.socket.ms_chat_socket.Entity.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}
