package com.chat.socket.ms_chat_socket.Service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.socket.ms_chat_socket.Dto.FileCreateDTO;
import com.chat.socket.ms_chat_socket.Entity.Files;
import com.chat.socket.ms_chat_socket.Repository.FilesRepository;
import com.chat.socket.ms_chat_socket.Service.IFileService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FileService implements IFileService {
    
    @Autowired private FilesRepository filesRepository;

    @Override
    public Files createFile(FileCreateDTO file) {
        return filesRepository.save(convertDTOtoEntity(file));
    }

    @Override
    public Optional<Files> findByID(Long id) {
        return filesRepository.findById(id);
    }

    @Override
    public Boolean deleteFile(Long id) {
        Optional<Files> entity = findByID(id);
        if (entity.isPresent()) {
            filesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Files convertDTOtoEntity(FileCreateDTO file) {
        Files entity = new Files();
        entity.setUrl(file.getUrl());
        entity.setDescription(file.getDescription());

        return entity;
    }
}
