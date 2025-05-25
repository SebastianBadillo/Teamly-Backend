package com.chat.socket.ms_chat_socket.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.socket.ms_chat_socket.Dto.TeamCreateDto;
import com.chat.socket.ms_chat_socket.Entity.Team;
import com.chat.socket.ms_chat_socket.Repository.TeamRepository;
import com.chat.socket.ms_chat_socket.Repository.UserRepository;
import com.chat.socket.ms_chat_socket.Service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public void createTeam(TeamCreateDto dto, String userEmail) throws Exception {
        if (teamRepository.existsByName(dto.getName())) {
            throw new Exception("Team already exists");
        }
        if (!userRepository.existsByEmail(userEmail)) {
            throw new Exception("User not found");
        }
        Team team = new Team();
        team.setName(dto.getName());
        team.setDescription(dto.getDescription());
        teamRepository.save(team);
    }
    @Override
    public void deleteTeam(Long id) throws Exception {
    if (!teamRepository.existsById(id)) {
        throw new Exception("El equipo no existe");
        }
    teamRepository.deleteById(id);
    }
    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    
}
