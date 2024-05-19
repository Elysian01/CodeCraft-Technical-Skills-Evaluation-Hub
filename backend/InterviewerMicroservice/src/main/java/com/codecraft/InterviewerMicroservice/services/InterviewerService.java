package com.codecraft.InterviewerMicroservice.services;

import com.codecraft.InterviewerMicroservice.dto.*;
import com.codecraft.InterviewerMicroservice.entities.Enrollment;
import com.codecraft.InterviewerMicroservice.entities.Job;

import java.util.List;

public interface InterviewerService {
    String login(String email, String password);
    int activeJobsCount(int id);
     List<Job> allactiveJobsCount();
    String createJob(JobPostingDTO jobPostingRequest);

    List<JobInfoDTO> getJobs(int id);

    List<JobEnrollmentInfoDTO> getJobEnrollments(Long jobId);

    boolean enrollInJob(JobEnrollDTO jobEnrollRequest);

    boolean updateTestScore(UpdateTestScoreDTO updateTestScoreRequest);

    boolean postInterviewRecord(PostInterviewRecordDTO postInterviewRecordRequest);

    InterviewRecordInfoDTO getInterviewRecord(Long interviewRecordId);
}
