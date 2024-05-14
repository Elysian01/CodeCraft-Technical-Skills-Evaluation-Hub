package com.codecraft.InterviewerMicroservice.services.implementations;

import com.codecraft.InterviewerMicroservice.dto.JobInfoDTO;
import com.codecraft.InterviewerMicroservice.dto.JobPostingDTO;
import com.codecraft.InterviewerMicroservice.entities.AllRequirements;
import com.codecraft.InterviewerMicroservice.entities.Interviewer;
import com.codecraft.InterviewerMicroservice.entities.Job;
import com.codecraft.InterviewerMicroservice.entities.JobRequirements;
import com.codecraft.InterviewerMicroservice.repository.AllRequirementsRepository;
import com.codecraft.InterviewerMicroservice.repository.InterviewerRepository;
import com.codecraft.InterviewerMicroservice.repository.JobRepository;
import com.codecraft.InterviewerMicroservice.services.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InterviewerServiceImpl implements InterviewerService {

    @Autowired
    InterviewerRepository interviewerRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    AllRequirementsRepository allRequirementsRepository;

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

        // Set the Interviewer
        Optional<Interviewer> interviewerOptional = interviewerRepository.findById(jobPostingRequest.getInterviewerId());
        if (interviewerOptional.isPresent()) {
            job.setInterviewer(interviewerOptional.get());

            // Create JobRequirement entities for each requirement
            List<JobRequirements> jobRequirements = new ArrayList<>();
            for (String requirementName : jobPostingRequest.getRequirements()) {
                // Find the AllRequirements entity by requirementName
                Optional<AllRequirements> requirementOptional = allRequirementsRepository.findByRequirementName(requirementName);
                if (requirementOptional.isPresent()) {
                    JobRequirements jobRequirement = new JobRequirements();
                    jobRequirement.setJob(job);
                    jobRequirement.setAllRequirements(requirementOptional.get());
                    jobRequirements.add(jobRequirement);
                } else {
                    return "Requirement '" + requirementName + "' not found in the system";
                }
            }
            job.setJobRequirements(jobRequirements); // Set the list of jobRequirements

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




    // Helper method to map Job entity to JobInfoDTO
    private JobInfoDTO mapJobToJobInfoDTO(Job job) {
        List<String> requirements = job.getJobRequirements().stream()
                .map(jobRequirement -> jobRequirement.getAllRequirements().getRequirementName())
                .collect(Collectors.toList());

        return new JobInfoDTO(
                job.getId(),
                job.getJobName(),
                job.getJobDescription(),
                job.getStatus(),
                job.getNoOfEnrollments(),
                job.getRoleType(),
                job.getInterviewer().getId(),
                requirements
        );
    }

    @Override
    public List<JobInfoDTO> getJobs(int id) {
        List<Job> jobs = jobRepository.findByInterviewerId(id);
        return jobs.stream().map(this::mapJobToJobInfoDTO).collect(Collectors.toList());
    }
}
