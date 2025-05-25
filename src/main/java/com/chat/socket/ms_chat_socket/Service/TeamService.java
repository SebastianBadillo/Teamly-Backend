package com.chat.socket.ms_chat_socket.Service;

import java.util.List;

import com.chat.socket.ms_chat_socket.Dto.TeamCreateDto;
import com.chat.socket.ms_chat_socket.Entity.Team;

public interface TeamService {
    void createTeam(TeamCreateDto dto, String userEmail) throws Exception;

    void deleteTeam(Long id) throws Exception;

    List<Team> getAllTeams();
    
}
