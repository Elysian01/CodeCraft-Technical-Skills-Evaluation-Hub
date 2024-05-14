package com.codecraft.InterviewerMicroservice.repository;

import com.codecraft.InterviewerMicroservice.entities.Interviewer;
import com.codecraft.InterviewerMicroservice.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository  extends JpaRepository<Job, Long> {
}
