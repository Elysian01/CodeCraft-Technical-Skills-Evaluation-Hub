package com.codecraft.CandidateMicroservice.services.implementations;

import com.codecraft.CandidateMicroservice.dto.JobApplyDTO;
import com.codecraft.CandidateMicroservice.entities.Applied;
import com.codecraft.CandidateMicroservice.entities.Candidate;
import com.codecraft.CandidateMicroservice.repository.AppliedRepository;
import com.codecraft.CandidateMicroservice.repository.CandidateRepository;
import com.codecraft.CandidateMicroservice.services.CandidateService;
import jakarta.persistence.EntityNotFoundException;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    private AppliedRepository appliedRepository;

    public String login(String email, String password) {
        Candidate candidate = candidateRepository.findByEmail(email);
        if (candidate != null && candidate.getPassword().equals(password)) {
            return "Authorized";
        }
        return null;
    }

    public String applyJob(JobApplyDTO jobRequest) {
        try {
            Candidate candidate = candidateRepository.findById(jobRequest.getCid())
                    .orElseThrow(() -> new EntityNotFoundException("Candidate not found with ID: " + jobRequest.getCid()));

            Applied applied = new Applied();
            applied.setCandidate(candidate);
            applied.setJid(jobRequest.getJid());
            applied.setJob_name(jobRequest.getJob_name());
            applied.setTest_score(jobRequest.getTest_score());
            applied.setApplied_status(jobRequest.getApplied_status());
            appliedRepository.save(applied);
            return "Success"; // You may return any token or message as needed
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Or handle error gracefully
        }
    }
}
