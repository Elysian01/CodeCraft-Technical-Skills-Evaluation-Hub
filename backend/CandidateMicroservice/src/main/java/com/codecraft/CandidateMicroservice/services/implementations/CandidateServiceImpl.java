package com.codecraft.CandidateMicroservice.services.implementations;

import com.codecraft.CandidateMicroservice.dto.AppliedJobDTO;
import com.codecraft.CandidateMicroservice.dto.JobApplyDTO;
import com.codecraft.CandidateMicroservice.entities.Applied;
import com.codecraft.CandidateMicroservice.entities.Candidate;
import com.codecraft.CandidateMicroservice.repository.AppliedRepository;
import com.codecraft.CandidateMicroservice.repository.CandidateRepository;
import com.codecraft.CandidateMicroservice.services.CandidateService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    private AppliedRepository appliedRepository;

    public String login(String email, String password) {
        Candidate candidate = candidateRepository.findByEmail(email);
        if (candidate != null && candidate.getPassword().equals(password)) {
            return candidate.getId().toString();
        }
        return null;
    }

    public String applyJob(JobApplyDTO jobRequest) {
        try {
            System.out.println(jobRequest);
            Candidate candidate = candidateRepository.findById(jobRequest.getCid())
                    .orElseThrow(() -> new EntityNotFoundException("Candidate not found with ID: " + jobRequest.getCid()));

            Applied applied = new Applied();
            applied.setCandidate(candidate);
            applied.setJid(jobRequest.getJid());
            applied.setJobName(jobRequest.getCompany_name());
            applied.setTestScore(jobRequest.getTest_score());
            applied.setAppliedStatus(jobRequest.getApplied_status());
            appliedRepository.save(applied);
            return "Success"; // You may return any token or message as needed
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Or handle error gracefully
        }
    }

    @Override
    public List<AppliedJobDTO> listOfAppliedJobs(Integer id) {
        List<Applied> appliedList = appliedRepository.findByCandidateId(id);
        List<AppliedJobDTO> appliedJobDTOList = new ArrayList<>();
        for (Applied applied : appliedList) {
            appliedJobDTOList.add(new AppliedJobDTO(
                    applied.getId(),
                    applied.getCandidate().getId(),
                    applied.getJid(),
                    applied.getJobName(),
                    applied.getTestScore(),
                    applied.getAppliedStatus()
            ));
        }
        return appliedJobDTOList;
    }

    @Override
    public boolean updateAppliedStatus(Integer id, String appliedStatus) {
        Optional<Applied> appliedOptional = appliedRepository.findById(id);
        if (appliedOptional.isPresent()) {
            Applied applied = appliedOptional.get();
            applied.setAppliedStatus(appliedStatus);
            appliedRepository.save(applied);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTestScore(Integer id, String testScore) {
        System.out.println("Test: "+ testScore);

        Optional<Applied> appliedOptional = appliedRepository.findById(id);
        if (appliedOptional.isPresent()) {
            Applied applied = appliedOptional.get();
            applied.setTestScore(testScore);
            appliedRepository.save(applied);
            return true;
        }
        return false;
    }
}
