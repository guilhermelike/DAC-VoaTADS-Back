package com.voatads.authentication.dto;

import java.util.UUID;

public class AuthDTO {

    private UUID id;
    private String login;
    private String password;
    private String type;
    private String salt;
    private UUID id_user;

    public AuthDTO(String login, String password, String type, String salt, UUID id_user) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.type = type;
        this.salt = salt;
        this.id_user = id_user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public UUID getIdUser() {
        return id_user;
    }

    public void setIdUser(UUID id_user) {
        this.id_user = id_user;
    }
}
