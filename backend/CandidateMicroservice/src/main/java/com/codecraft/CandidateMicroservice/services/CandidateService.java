package com.codecraft.CandidateMicroservice.services;

import com.codecraft.CandidateMicroservice.dto.JobApplyDTO;

public interface CandidateService {
    String login(String email, String password);

    String applyJob(JobApplyDTO jobRequest);
}
