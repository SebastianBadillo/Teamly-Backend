package com.chat.socket.ms_chat_socket.Service.Impl;

import com.chat.socket.ms_chat_socket.Dto.AuthResponse;
import com.chat.socket.ms_chat_socket.Dto.LoginRequest;
import com.chat.socket.ms_chat_socket.Dto.RegisterRequest;
import com.chat.socket.ms_chat_socket.Entity.User;
import com.chat.socket.ms_chat_socket.Repository.UserRepository;
import com.chat.socket.ms_chat_socket.Service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {

            throw new RuntimeException("El correo ya está registrado");
        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return new AuthResponse("Usuario registrado con éxito", user.getEmail(), user.getFirstName(), user.getId());
    }
    @Override
    public AuthResponse login(LoginRequest request) {
        if(!userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El correo no está registrado");
        }
        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return new AuthResponse("Inicio de sesión exitoso", user.get().getEmail(), user.get().getFirstName(), user.get().getId());
    }
    @Override
    public Boolean isEmailRegistered(String email) {
        return userRepository.existsByEmail(email);
    }


}
