package com.chat.socket.ms_chat_socket.Service.Impl;

import com.chat.socket.ms_chat_socket.Dto.UserDto;
import com.chat.socket.ms_chat_socket.Entity.Role;
import com.chat.socket.ms_chat_socket.Entity.User;
import com.chat.socket.ms_chat_socket.Mappers.UserMapper;
import com.chat.socket.ms_chat_socket.Repository.RoleRepository;
import com.chat.socket.ms_chat_socket.Repository.UserRepository;
import com.chat.socket.ms_chat_socket.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private RoleRepository roleRepository;
    public UserServiceImpl(UserRepository theUserRepository, RoleRepository roleRepository) {
            this.userRepository = theUserRepository;
            this.roleRepository = roleRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto assignRoleToUser(Long userId, String roleName) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found"));

        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }
        if (user.getRoles().contains(role)) {
            throw new IllegalArgumentException("User already has this role");
        }

        user.getRoles().add(role);
        User updatedUser = userRepository.save(user);
        return UserMapper.toDto(updatedUser);
    }
}
