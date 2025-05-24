package com.chat.socket.ms_chat_socket.Dto;

public class AssignRoleRequest {
    private Long userId;
    private String roleName;

    public AssignRoleRequest() {}

    public AssignRoleRequest(Long userId, String roleName) {
        this.userId = userId;
        this.roleName = roleName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
