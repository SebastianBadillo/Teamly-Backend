package com.chat.socket.ms_chat_socket.Controller;

import com.chat.socket.ms_chat_socket.Dto.AuthResponse;
import com.chat.socket.ms_chat_socket.Dto.LoginRequest;
import com.chat.socket.ms_chat_socket.Dto.RegisterRequest;
import com.chat.socket.ms_chat_socket.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        if(authService.isEmailRegistered(request.getEmail())) {
            return ResponseEntity.badRequest().body(new AuthResponse("El correo ya está registrado", null, null, null));
        }
        if (request.getFirstName() == null || request.getLastName() == null || request.getEmail() == null || request.getPassword() == null) {
            return ResponseEntity.badRequest().body(new AuthResponse("All fields are required", null, null, null));
        }
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        if(!authService.isEmailRegistered(request.getEmail())) {
            System.out.println(request.getEmail());
            return ResponseEntity.badRequest().body(new AuthResponse("El correo no esta registrado", null, null, null));

        }

        return ResponseEntity.ok(authService.login(request));
    }
}

