package com.codecraft.InterviewerMicroservice.services.implementations;

import com.codecraft.InterviewerMicroservice.dto.*;
import com.codecraft.InterviewerMicroservice.entities.*;
import com.codecraft.InterviewerMicroservice.repository.*;
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

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    private InterviewRecordRepository interviewRecordRepository;

    @Autowired
    private FulfilledRequirementsRepository fulfilledRequirementsRepository;

    public String login(String email, String password) {
        Interviewer candidate = interviewerRepository.findByEmail(email);
        if (candidate != null && candidate.getPassword().equals(password)) {
            return candidate.getId().toString();
        }
        return null;
    }
    @Override
    public int activeJobsCount(int id){
        System.out.println(id);
        Optional<Interviewer> i= interviewerRepository.findById(id);
        System.out.println(i.get().getEmail());
        return jobRepository.countAllByInterviewerId(id);
    }
    @Override
    public List<Job> allactiveJobsCount(){

        return jobRepository.findAllByStatus("open");
    }

    @Override
    public String createJob(JobPostingDTO jobPostingRequest) {
        System.out.println(jobPostingRequest);
        // Mapping JobPostingDTO to Job entity
        Job job = new Job();
        job.setCompany(jobPostingRequest.getCompany());
        job.setContact(jobPostingRequest.getContact());
        System.out.println(true);
        job.setJobDescription(jobPostingRequest.getJobDescription());
        System.out.println(true);
        job.setStatus(jobPostingRequest.getStatus());
//        job.setNoOfEnrollments(jobPostingRequest.getNoOfEnrollments());
        job.setRoleType(jobPostingRequest.getRoleType());
        System.out.println(true);

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
                job.getCompany(),
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
        return jobs.stream()
                .filter(job -> job.getStatus().equals("open"))
                .map(this::mapJobToJobInfoDTO)
                .collect(Collectors.toList());

    }

    private JobEnrollmentInfoDTO mapToJobEnrollmentInfoDTO(Enrollment enrollment) {
        return new JobEnrollmentInfoDTO(
                enrollment.getCandidateId(),
                enrollment.getCandidateName(),
                enrollment.getTestScore(),
                enrollment.getJob().getId(),
                enrollment.getInterviewRecord() != null ? enrollment.getInterviewRecord().getId() : null
        );
    }

    @Override
    public List<JobEnrollmentInfoDTO> getJobEnrollments(Long jobId) {
        List<Enrollment> enrollments = enrollmentRepository.findByJobId(jobId);
        return enrollments.stream()
                .map(this::mapToJobEnrollmentInfoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean enrollInJob(JobEnrollDTO jobEnrollRequest) {
        try {
            // Fetch the job entity by its ID
            Optional<Job> optionalJob = jobRepository.findById(jobEnrollRequest.getJobId());
            if (optionalJob.isPresent()) {
                Job job = optionalJob.get();

                // Create a new enrollment record
                Enrollment enrollment = new Enrollment();
                enrollment.setCandidateId(jobEnrollRequest.getCandidateId());
                enrollment.setJob(job);
                enrollment.setCandidateName(jobEnrollRequest.getCandidateName());

                // Save the enrollment record
                enrollmentRepository.save(enrollment);

                return true;
            } else {
                return false; // Job not found
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log or handle the exception as needed
            return false; // Error occurred during enrollment
        }
    }

    @Override
    public boolean updateTestScore(UpdateTestScoreDTO updateTestScoreRequest) {
        try {
            // Fetch the enrollment record by candidateId and jobId
            Optional<Enrollment> optionalEnrollment = enrollmentRepository.findByCandidateIdAndJobId(
                    updateTestScoreRequest.getCandidateId(),
                    updateTestScoreRequest.getJobId());

            if (optionalEnrollment.isPresent()) {
                Enrollment enrollment = optionalEnrollment.get();
                // Update the test score
                enrollment.setTestScore(updateTestScoreRequest.getTestScore());

                // Save the updated enrollment record
                enrollmentRepository.save(enrollment);

                return true;
            } else {
                return false; // Enrollment record not found
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log or handle the exception as needed
            return false; // Error occurred during test score update
        }
    }

    @Override
    public boolean postInterviewRecord(PostInterviewRecordDTO postInterviewRecordRequest) {
        // Create and save InterviewRecord entity
        InterviewRecord interviewRecord = new InterviewRecord();
        interviewRecord.setCandidateId(postInterviewRecordRequest.getCandidateId());
        interviewRecord.setJob(jobRepository.findById(postInterviewRecordRequest.getJobId()).orElse(null));
        interviewRecord.setPositiveFeedback(postInterviewRecordRequest.getPositiveFeedback());
        interviewRecord.setNegativeFeedback(postInterviewRecordRequest.getNegativeFeedback());
        interviewRecordRepository.save(interviewRecord);

        // Fetch AllRequirements entities based on provided requirement names
        List<AllRequirements> allRequirements = allRequirementsRepository.findByRequirementNameIn(
                postInterviewRecordRequest.getFullfilledRequirements());

        // Create and save FulfilledRequirements entity
        FulfilledRequirements fulfilledRequirements = new FulfilledRequirements();
        fulfilledRequirements.setInterviewRecordId(interviewRecord.getId());
        fulfilledRequirements.setFulfilledRequirements(new HashSet<>(allRequirements));
        fulfilledRequirementsRepository.save(fulfilledRequirements);

        // Update corresponding Enrollment entity
        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findByCandidateIdAndJobId(
                postInterviewRecordRequest.getCandidateId(),
                postInterviewRecordRequest.getJobId());
        if (optionalEnrollment.isPresent()) {
            Enrollment enrollment = optionalEnrollment.get();
            enrollment.setInterviewRecord(interviewRecord);
            enrollmentRepository.save(enrollment);
        }
        return true;
    }

    @Override
    public InterviewRecordInfoDTO getInterviewRecord(Long interviewRecordId) {
        InterviewRecord interviewRecord = interviewRecordRepository.findById(interviewRecordId)
                .orElseThrow(() -> new RuntimeException("Interview record not found"));

        Job job = interviewRecord.getJob();

        List<String> fulfilledRequirements = fulfilledRequirementsRepository
                .findByInterviewRecordId(interviewRecordId)
                .stream()
                .flatMap(fr -> fr.getFulfilledRequirements().stream())
                .map(AllRequirements::getRequirementName)
                .collect(Collectors.toList());

        List<String> requirements = job.getAllRequirements()
                .stream()
                .map(AllRequirements::getRequirementName)
                .collect(Collectors.toList());

        return new InterviewRecordInfoDTO(
                job.getId(),
                job.getCompany(),
                job.getJobDescription(),
                requirements,
                interviewRecord.getCandidateId(),
                interviewRecord.getPositiveFeedback(),
                interviewRecord.getNegativeFeedback(),
                fulfilledRequirements
        );
    }

}
