package com.chat.socket.ms_chat_socket.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamCreateDto {
    private String name;
    private String description;
}
