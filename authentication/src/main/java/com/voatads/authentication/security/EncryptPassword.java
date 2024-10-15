package com.voatads.authentication.security;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptPassword {
    
    public static String createSaltForPassword() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    public static String encryptPassword(String password, String salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            String saltPlusPassword = salt + password;
            byte[] hashBytes = messageDigest.digest(saltPlusPassword.getBytes());
            String hashPassword = Base64.getEncoder().encodeToString(hashBytes);
            return hashPassword;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar o hash da senha", e);
        }   
    }

    public static boolean verifyPassword(String password, String passwordHash, String saltUser) {
        String passHashForCompare = encryptPassword(password, saltUser);
        return passwordHash.equals(passHashForCompare);
    }
}
