package com.chat.socket.ms_chat_socket.Controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chat.socket.ms_chat_socket.Dto.FileCreateDTO;
import com.chat.socket.ms_chat_socket.Entity.Files;
import com.chat.socket.ms_chat_socket.Service.Impl.FileService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/files")
public class FilesController {
    
    private final String url = "./uploads/";

    @Autowired private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<Files> uploadFile(@RequestParam("file") MultipartFile file,
            @RequestParam("path") String path,
            @RequestParam("description") String description) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Path uploadpath = Paths.get(url + path + "/");
            if (!java.nio.file.Files.exists(uploadpath)) {
                java.nio.file.Files.createDirectories(uploadpath);
            }

            String fileName = file.getOriginalFilename();
            if (fileName == null) {
                return ResponseEntity.badRequest().build();
            }

            String originalFilename = org.springframework.util.StringUtils.cleanPath(fileName);
            String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
            String newFileName = UUID.randomUUID() + extension;

            Path destinationFile = uploadpath.resolve(newFileName);

            java.nio.file.Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            FileCreateDTO archivo = new FileCreateDTO();
            archivo.setDescription(description);
            archivo.setUrl(destinationFile.toString());

            return ResponseEntity.ok(fileService.createFile(archivo));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFiles(@PathVariable("id") Long id) {
        try {
            Files file = fileService.findByID(id).get();
            Path path = Paths.get(file.getUrl());
            byte[] fileBytes = java.nio.file.Files.readAllBytes(path);

            String contentType = java.nio.file.Files.probeContentType(path);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(fileBytes);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteFile(@PathVariable("id") Long id) {
        Optional<Files> entity = fileService.findByID(id);
        if (entity.isPresent()) {
            fileService.deleteFile(id);
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.notFound().build();
    }
    
}
