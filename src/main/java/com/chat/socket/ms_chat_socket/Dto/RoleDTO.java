package com.chat.socket.ms_chat_socket.Dto;

public class RoleDTO {
    private Long id;
    private String name;

    public RoleDTO() {}

    public RoleDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
