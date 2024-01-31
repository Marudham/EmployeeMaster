package com.employeemaster.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String generateUniqueToken() {
        return UUID.randomUUID().toString();
    }
}

