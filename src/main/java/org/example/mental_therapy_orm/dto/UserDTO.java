package org.example.mental_therapy_orm.dto;


public class UserDTO {
    private String username;
    private String password;
    private String role;

    public UserDTO() {}
    public UserDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public String getUsername() { return this.username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return this.role; }
    public void setRole(String role) { this.role = role; }
}
