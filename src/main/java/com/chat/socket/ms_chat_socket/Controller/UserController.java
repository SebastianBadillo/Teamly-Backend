package com.chat.socket.ms_chat_socket.Controller;

import com.chat.socket.ms_chat_socket.Dto.AssignRoleRequest;
import com.chat.socket.ms_chat_socket.Dto.UserDto;
import com.chat.socket.ms_chat_socket.Service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http:localhost:4200", allowedHeaders = "*")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/assign-role")
    @PreAuthorize("hasRole('ADMIN')") 
    public ResponseEntity<?> assignRole(@Valid @RequestBody AssignRoleRequest request) {
        try {
            UserDto updatedUser = userService.assignRoleToUser(request.getUserId(), request.getRoleName());
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @GetMapping("/admin-panel")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminPanel() {
        return ResponseEntity.ok("Welcome to the admin panel!");
    }
}
