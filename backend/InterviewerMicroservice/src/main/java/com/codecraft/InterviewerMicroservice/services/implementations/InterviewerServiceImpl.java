package com.codecraft.InterviewerMicroservice.services.implementations;

import com.codecraft.InterviewerMicroservice.dto.JobInfoDTO;
import com.codecraft.InterviewerMicroservice.dto.JobPostingDTO;
import com.codecraft.InterviewerMicroservice.dto.QuestionDTO;
import com.codecraft.InterviewerMicroservice.entities.*;
import com.codecraft.InterviewerMicroservice.repository.AllRequirementsRepository;
import com.codecraft.InterviewerMicroservice.repository.InterviewerRepository;
import com.codecraft.InterviewerMicroservice.repository.JobRepository;
import com.codecraft.InterviewerMicroservice.repository.QuestionRepository;
import com.codecraft.InterviewerMicroservice.services.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InterviewerServiceImpl implements InterviewerService {

    @Autowired
    InterviewerRepository interviewerRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    AllRequirementsRepository allRequirementsRepository;

    @Autowired
    QuestionRepository questionRepository;

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

            // Set Requirements
            Set<AllRequirements> allRequirements = new HashSet<>();
            for (String requirementName : jobPostingRequest.getRequirements()) {
                Optional<AllRequirements> requirementOptional = allRequirementsRepository.findByRequirementName(requirementName);
                requirementOptional.ifPresent(allRequirements::add);
            }
            job.setAllRequirements(allRequirements);

            // Set Questions
            Set<Question> questions = new HashSet<>();
            for (Long questionId : jobPostingRequest.getQuestions()) {
                Optional<Question> questionOptional = questionRepository.findById(questionId);
                questionOptional.ifPresent(questions::add);
            }
            job.setQuestions(questions);

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

    private JobInfoDTO mapJobToJobInfoDTO(Job job) {
        List<String> requirements = job.getAllRequirements().stream()
                .map(AllRequirements::getRequirementName)
                .collect(Collectors.toList());

        List<QuestionDTO> questions = job.getQuestions().stream()
                .map(this::mapQuestionToQuestionDTO)
                .collect(Collectors.toList());

        return new JobInfoDTO(
                job.getId(),
                job.getJobName(),
                job.getJobDescription(),
                job.getStatus(),
                job.getNoOfEnrollments(),
                job.getRoleType(),
                job.getInterviewer().getId(),
                requirements,
                questions
        );
    }

    private QuestionDTO mapQuestionToQuestionDTO(Question question) {
        return new QuestionDTO(
                question.getId(),
                question.getQuestion(),
                question.getTopic(),
                question.getQuestionName(),
                question.getTestcases()
        );
    }

    public List<JobInfoDTO> getJobs(int id) {
        List<Job> jobs = jobRepository.findByInterviewerId(id);
        return jobs.stream().map(this::mapJobToJobInfoDTO).collect(Collectors.toList());
    }

}
