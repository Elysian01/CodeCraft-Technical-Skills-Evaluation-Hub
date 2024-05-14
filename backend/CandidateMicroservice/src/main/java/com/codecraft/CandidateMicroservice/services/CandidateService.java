package com.codecraft.CandidateMicroservice.services;

import com.codecraft.CandidateMicroservice.dto.AppliedJobDTO;
import com.codecraft.CandidateMicroservice.dto.JobApplyDTO;

import java.util.List;

public interface CandidateService {
    String login(String email, String password);

    String applyJob(JobApplyDTO jobRequest);

    List<AppliedJobDTO> listOfAppliedJobs(Integer id);

    boolean updateAppliedStatus(Integer id, String appliedStatus);

    boolean updateTestScore(Integer id, String testScore);
}
