package com.voatads.authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voatads.authentication.dto.AuthDTO;
import com.voatads.authentication.model.Auth;
import com.voatads.authentication.security.EncryptPassword;
import com.voatads.authentication.services.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@CrossOrigin
@RestController
public class AuthController {
    @Autowired
    AuthService authService;
    // Senha criptografada SHA256+SALT

    @PostMapping("/login")
    public ResponseEntity<Auth> login(@RequestBody AuthDTO auth) {
        try {
            // Procurar auth pelo email
            Auth authUser = authService.findAuthByLogin(auth.getLogin());
            if (authUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                // Comparar senhas
                Boolean verificatedPassword = EncryptPassword.verifyPassword(auth.getPassword(), authUser.getPassword(), authUser.getSalt());
                if (verificatedPassword) {
                    return ResponseEntity.ok().body(authUser);
                }
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login/create")
    public ResponseEntity<String> createLogin(@RequestBody AuthDTO authDTO) {
        try {
            String salt = EncryptPassword.createSaltForPassword();
            authDTO.setSalt(salt);
            String encryptedPassword = EncryptPassword.encryptPassword(authDTO.getPassword(), salt);
            authDTO.setPassword(encryptedPassword);
            // Relacionar id e type do usuário ao login
            authDTO.setIdUser(authDTO.getIdUser());
            authDTO.setType("2");
            Auth authEntity = authService.createLogin(authDTO);
            if (authEntity != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Login criado com sucesso");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Resquest"); // Ver status code
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + e );
        }
    }

    @DeleteMapping("login/{id}")
    public ResponseEntity<String> deleteAuth(@PathVariable UUID idUser) {
        try {
            authService.deleteAuth(idUser);
            return ResponseEntity.ok().body("Login excluido com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + e);
        }
    }
    
    // Revisar código
    @PutMapping("login/{id}")
    public ResponseEntity<String> updateAuth(@PathVariable UUID idUser, @RequestBody Auth auth) {
        try {
            // Encontrar login de usuário
            Auth authUser = authService.findAuthById(idUser);
            // Criar hash de senha
            String salt = EncryptPassword.createSaltForPassword();
            authUser.setSalt(salt);
            String encryptedPassword = EncryptPassword.encryptPassword(auth.getPassword(), salt);
            authUser.setPassword(encryptedPassword);
            // Salvar alterações
            authService.updateAuth(authUser);
            return ResponseEntity.ok().body("Login atualizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + e);
        }
    }
}
