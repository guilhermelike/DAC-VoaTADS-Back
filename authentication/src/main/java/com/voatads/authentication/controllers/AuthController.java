package com.voatads.authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> login(@RequestBody Auth auth) {
        try {
            // Procurar auth pelo login
            Auth authUser = authService.findAuthByLogin(auth.getLogin());
            if (authUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            } else {
                // Comparar senhas
                Boolean verificatedPassword = EncryptPassword.verifyPassword(auth.getPassword(), authUser.getPassword(), authUser.getSalt());
                if (verificatedPassword) {
                    return ResponseEntity.ok().body("Login realizado com sucesso");
                }
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credencial inválida. Verifique sua senha e tente novamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + e);
        }
    }

    @PostMapping("/login/create")
    public ResponseEntity<String> createLogin(@RequestBody Auth auth) {
        try {
            String salt = EncryptPassword.createSaltForPassword();
            auth.setSalt(salt);
            String encryptedPassword = EncryptPassword.encryptPassword(auth.getPassword(), salt);
            auth.setPassword(encryptedPassword);
            Auth authEntity = authService.createLogin(auth);
            if (authEntity == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Login criado com sucesso");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login criado com sucesso"); // Ver status code
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
