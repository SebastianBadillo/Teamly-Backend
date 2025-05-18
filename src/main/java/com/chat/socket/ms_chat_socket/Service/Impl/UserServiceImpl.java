package com.chat.socket.ms_chat_socket.Service.Impl;

import com.chat.socket.ms_chat_socket.Dto.UserDto;
import com.chat.socket.ms_chat_socket.Entity.User;
import com.chat.socket.ms_chat_socket.Mappers.UserMapper;
import com.chat.socket.ms_chat_socket.Repository.UserRepository;
import com.chat.socket.ms_chat_socket.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository theUserRepository) {
            this.userRepository = theUserRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
}
