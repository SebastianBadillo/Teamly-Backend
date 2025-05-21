package com.chat.socket.ms_chat_socket.Service;

import java.util.Optional;

import com.chat.socket.ms_chat_socket.Dto.FileCreateDTO;
import com.chat.socket.ms_chat_socket.Entity.Files;

public interface IFileService {
    Files createFile(FileCreateDTO file);
    Optional<Files> findByID(Long id);
    Boolean deleteFile(Long id);
}
