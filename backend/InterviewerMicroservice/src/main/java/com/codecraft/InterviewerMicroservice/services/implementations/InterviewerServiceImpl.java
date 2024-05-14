package com.codecraft.InterviewerMicroservice.services.implementations;

import com.codecraft.InterviewerMicroservice.dto.JobPostingDTO;
import com.codecraft.InterviewerMicroservice.entities.Interviewer;
import com.codecraft.InterviewerMicroservice.entities.Job;
import com.codecraft.InterviewerMicroservice.repository.InterviewerRepository;
import com.codecraft.InterviewerMicroservice.repository.JobRepository;
import com.codecraft.InterviewerMicroservice.services.InterviewerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InterviewerServiceImpl implements InterviewerService {

    @Autowired
    InterviewerRepository interviewerRepository;

    @Autowired
    JobRepository jobRepository;

    public String login(String email, String password) {
        Interviewer candidate = interviewerRepository.findByEmail(email);
        if (candidate != null && candidate.getPassword().equals(password)) {
            return "Authorized";
        }
        return null;
    }

    @Override
    public String createJob(JobPostingDTO jobPostingRequest) {
        // Mapping JobPostingDTO to Job entity
        Job job = new Job();
        job.setJobName(jobPostingRequest.getJobName());
        job.setJobDescription(jobPostingRequest.getJobDescription());
        job.setStatus(jobPostingRequest.getStatus());
        job.setNoOfEnrollments(jobPostingRequest.getNoOfEnrollments());
        job.setRoleType(jobPostingRequest.getRoleType());

        Optional<Interviewer> interviewerOptional = interviewerRepository.findById(jobPostingRequest.getInterviewerId());
        if (interviewerOptional.isPresent()) {
            job.setInterviewer(interviewerOptional.get());
            Job savedJob = jobRepository.save(job);
            if (savedJob != null) {
                return "Job created successfully";
            } else {
                return "Failed to create job";
            }
        } else {
            return "Interviewer not found for the given ID";
        }
    }
}
