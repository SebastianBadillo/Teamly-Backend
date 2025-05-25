package com.chat.socket.ms_chat_socket.Controller;

import com.chat.socket.ms_chat_socket.Dto.TeamCreateDto;
import com.chat.socket.ms_chat_socket.Entity.Team;
import com.chat.socket.ms_chat_socket.Entity.User;
import com.chat.socket.ms_chat_socket.Repository.UserRepository;
import com.chat.socket.ms_chat_socket.Service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserRepository userRepository; // Inject UserRepository

    @PostMapping("/create")
    public ResponseEntity<String> createTeam(@RequestBody TeamCreateDto dto, Principal principal) {
        try {
            teamService.createTeam(dto, principal.getName());
            return ResponseEntity.ok("Equipo creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> listTeams(@RequestParam String userEmail) {
        User user = userRepository.findByEmail(userEmail); // Use correct repository and type
        if (user == null || !"admin".equalsIgnoreCase(user.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para ver equipos");
        }
        List<Team> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id, @RequestParam String userEmail) throws Exception {
        User user = userRepository.findByEmail(userEmail); // Use correct repository and type
        if (user == null || !"admin".equalsIgnoreCase(user.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para eliminar equipos");
        }
        teamService.deleteTeam(id);
        return ResponseEntity.ok("Equipo eliminado");
    }
}
