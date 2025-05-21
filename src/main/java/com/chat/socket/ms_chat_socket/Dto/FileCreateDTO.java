package com.chat.socket.ms_chat_socket.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileCreateDTO {
    private String url;
    private String description;
}
