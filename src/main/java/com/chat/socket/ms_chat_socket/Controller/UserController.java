package com.chat.socket.ms_chat_socket.Controller;

import com.chat.socket.ms_chat_socket.Dto.UserDto;
import com.chat.socket.ms_chat_socket.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
}
