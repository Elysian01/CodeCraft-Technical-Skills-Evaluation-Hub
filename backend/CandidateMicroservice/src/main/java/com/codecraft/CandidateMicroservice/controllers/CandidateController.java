package com.codecraft.CandidateMicroservice.controllers;

import com.codecraft.CandidateMicroservice.dto.LoginRequestDTO;
import com.codecraft.CandidateMicroservice.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {
        String token = candidateService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials or User is Unauthorized");
        }
    }
}
