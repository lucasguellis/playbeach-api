package com.pbio.playbeach.controllers;
import com.pbio.playbeach.entities.dto.UserLoginRequestDTO;
import com.pbio.playbeach.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        String token = jwtUtil.generateToken(userLoginRequestDTO.getUsername());
        return ResponseEntity.ok().body(Map.of("token", token));
    }
}
