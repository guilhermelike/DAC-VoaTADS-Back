package com.voatads.authentication.services;

import com.voatads.authentication.dto.AuthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.authentication.model.Auth;
import com.voatads.authentication.repository.AuthRepository;

import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    public Auth getLogin(Auth auth) {
        Auth responseAuth = authRepository.findByLoginAndPassword(auth.getLogin(), auth.getPassword());
        return responseAuth;
    }

    public Auth findAuthByLogin(String login) {
        return authRepository.findByLogin(login);
    }

    public Auth findAuthById(UUID id) {
        return authRepository.findById(id).get();
    }

    public Auth createLogin(AuthDTO authDTO) {
        Auth auth = new Auth();
        auth.setId(authDTO.getId());
        auth.setLogin(authDTO.getLogin());
        auth.setPassword(authDTO.getPassword());
        auth.setType(authDTO.getType());
        auth.setSalt(authDTO.getSalt());
        auth.setIdUser(authDTO.getIdUser());
        return authRepository.save(auth);
    }

    public Auth updateAuth(Auth authUser) {
        return authRepository.save(authUser);
    }

    public Boolean deleteAuth(UUID idUser) {
        try {
            authRepository.deleteById(idUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
