package com.employeemaster.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncryptionService {

    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordEncryptionService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String encryptPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
}
