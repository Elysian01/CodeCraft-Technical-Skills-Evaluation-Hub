package com.codecraft.InterviewerMicroservice.services;

import com.codecraft.InterviewerMicroservice.dto.JobInfoDTO;
import com.codecraft.InterviewerMicroservice.dto.JobPostingDTO;

import java.util.List;

public interface InterviewerService {
    String login(String email, String password);

    String createJob(JobPostingDTO jobPostingRequest);

    List<JobInfoDTO> getJobs(int id);
}
