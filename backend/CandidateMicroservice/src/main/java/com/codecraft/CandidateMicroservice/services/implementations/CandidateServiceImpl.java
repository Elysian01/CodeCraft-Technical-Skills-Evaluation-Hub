package com.codecraft.CandidateMicroservice.services.implementations;

import com.codecraft.CandidateMicroservice.entities.Candidate;
import com.codecraft.CandidateMicroservice.repository.CandidateRepository;
import com.codecraft.CandidateMicroservice.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    CandidateRepository candidateRepository;

    public String login(String email, String password) {
        Candidate candidate = candidateRepository.findByEmail(email);
        if (candidate != null && candidate.getPassword().equals(password)) {
            return "Authorized";
        }
        return null;
    }
}
