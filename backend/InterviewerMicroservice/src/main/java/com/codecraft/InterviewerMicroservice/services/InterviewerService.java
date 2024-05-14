package com.codecraft.InterviewerMicroservice.services;

import com.codecraft.InterviewerMicroservice.dto.*;
import com.codecraft.InterviewerMicroservice.entities.Enrollment;

import java.util.List;

public interface InterviewerService {
    String login(String email, String password);

    String createJob(JobPostingDTO jobPostingRequest);

    List<JobInfoDTO> getJobs(int id);

    List<JobEnrollmentInfoDTO> getJobEnrollments(Long jobId);

    boolean enrollInJob(JobEnrollDTO jobEnrollRequest);

    boolean updateTestScore(UpdateTestScoreDTO updateTestScoreRequest);
}
